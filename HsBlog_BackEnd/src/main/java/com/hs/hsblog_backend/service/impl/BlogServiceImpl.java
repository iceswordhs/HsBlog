package com.hs.hsblog_backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.constants.RedisKey;
import com.hs.hsblog_backend.constants.exception.ServiceException;
import com.hs.hsblog_backend.entity.Blog;
import com.hs.hsblog_backend.entity.Category;
import com.hs.hsblog_backend.entity.Tag;
import com.hs.hsblog_backend.model.dto.BlogView;
import com.hs.hsblog_backend.model.vo.*;
import com.hs.hsblog_backend.repository.BlogMapper;
import com.hs.hsblog_backend.service.*;
import com.hs.hsblog_backend.util.JacksonUtils;
import com.hs.hsblog_backend.util.StringUtils;
import com.hs.hsblog_backend.util.commarkUtil.MarkDownToHTMLUtil;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author Hs
 * @Date 2021/12/3 20:09
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    TagService tagService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    RedisService redisService;

    //随机博客显示5条
    private static final int randomBlogLimitNum = 5;
    //最新博客显示3条
    private static final int newBlogPageSize = 3;
    // 博客简介列表排序方式
    private static final String orderBy = "top desc, create_time desc";
    private static final Integer pageSize=5;


    /**
     * 项目启动时，保存所有博客的浏览量到Redis
     */
    @PostConstruct
    private void saveBlogViewsToRedis() {
        String redisKey = RedisKey.BLOG_VIEWS_MAP;
        // Redis中没有存储博客浏览量的Hash
        if (!redisService.hasKey(redisKey)) {
            // 从数据库中读取并存入Redis
            Map<Long, Integer> blogViewsMap = getBlogViewsMap();
            redisService.saveMapToHash(redisKey, blogViewsMap);
        }
    }

    private Map<Long, Integer> getBlogViewsMap() {
        List<BlogView> blogViewList = blogMapper.getBlogViewsList();
        Map<Long, Integer> blogViewsMap = new HashMap<>();
        for (BlogView blogView : blogViewList) {
            blogViewsMap.put(blogView.getId(), blogView.getViews());
        }
        return blogViewsMap;
    }

    @Override
    public PageInfo<BlogListItem> getAllBlog() {
        List<BlogListItem> allBlog = blogMapper.findAllBlog();
        return new PageInfo<>(allBlog);
    }

    @Override
    public List<SearchBlog> getSearchBlogListIsPublished(String query) {
        List<SearchBlog> searchBlogs = blogMapper.getSearchBlogListIsPublished(query);
        for (SearchBlog searchBlog : searchBlogs) {
            if (searchBlog.getTitle().contains(query)){
                searchBlog.setContent("");
                continue;
            }
            String content = searchBlog.getContent();
            int contentLength = content.length();
            int index = content.indexOf(query) - 10;
            index = index < 0 ? 0 : index;
            int end = index + 21;//以关键字字符串为中心返回21个字
            end = end > contentLength - 1 ? contentLength - 1 : end;
            searchBlog.setContent(content.substring(index, end));
        }
        return searchBlogs;
    }

    @Override
    public Blog getBlogById(Long id) {
        Blog blog = blogMapper.getBlogById(id);
        if (blog==null){
            throw ServiceException.create("该博客不存在");
        }
        updateViewsToRedis(id);
        processBlog(blog);
        return blog;
    }

    @Override
    public Blog getBlogEditById(Long id) {
        Blog blog = blogMapper.getBlogEditById(id);
        // 添加标签
        blog.setTags(tagService.getTagByBlogId(blog.getId()));
        return blog;
    }

    @Override
    public void updateViewsToRedis(Long blogId) {
        redisService.incrementByHashKey(RedisKey.BLOG_VIEWS_MAP, blogId, 1);
    }

    @Override
    public PageInfo<BlogListItem> getPageBlogIsPublished(int pageNum){
        String redisKey = RedisKey.HOME_BLOG_INFO_LIST;
        // redis已有当前页缓存
        PageInfo<BlogListItem> getBlogListItemPageResultByHash = redisService.getBlogListItemPageResultByHash(redisKey, pageNum);
        // 如果有缓存，将缓存中的view设为最新
        if (getBlogListItemPageResultByHash != null) {
            setBlogViewsFromRedisToPageResult(getBlogListItemPageResultByHash);
            return getBlogListItemPageResultByHash;
        }

        // 没有缓存，将结果处理并设置最新的view放入Redis并返回
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<BlogListItem> blogs = blogMapper.findAllPublishedBlog();
        processBlogListItem(blogs);
        PageInfo<BlogListItem> blogListItemPageInfo = new PageInfo<>(blogs);
        // 向Redis中添加缓存
        redisService.saveKVToHash(redisKey, pageNum, blogListItemPageInfo);
        return blogListItemPageInfo;
    }

    /**
     * 将pageResult中博客对象的浏览量设置为Redis中的最新值
     *
     * @param pageResult
     */
    private void setBlogViewsFromRedisToPageResult(PageInfo<BlogListItem> pageResult) {
        String redisKey = RedisKey.BLOG_VIEWS_MAP;
        List<BlogListItem> blogInfos = pageResult.getList();
        for (int i = 0; i < blogInfos.size(); i++) {
            BlogListItem blogListItem = JacksonUtils.convertValue(blogInfos.get(i), BlogListItem.class);
            Long blogId = blogListItem.getId();
            int view = (int) redisService.getValueByHashKey(redisKey, blogId);
            blogListItem.setViews(view);
            blogInfos.set(i, blogListItem);
        }
    }

    /**
     * 在返回博客时，对博客进行处理 添加标签，转为html
     * @author huangshuai
     * @Date 2021/12/29 14:36
     * @param blogs 要处理的博客
     */
    private void processBlog(List<Blog> blogs){
        if (CollectionUtils.isEmpty(blogs)) return;
        for (Blog blog : blogs) {
            if (blog==null) continue;
            // 添加标签
            blog.setTags(tagService.getTagByBlogId(blog.getId()));
            // 将正文变为html
            if (!StringUtils.isEmpty(blog.getContent()))
            blog.setContent(MarkDownToHTMLUtil.markdownToHtml(blog.getContent()));
            // 将描述转为html
            if(!StringUtils.isEmpty(blog.getDescription()))
            blog.setDescription(MarkDownToHTMLUtil.markdownToHtml(blog.getDescription()));
            // 设置最新的view
            int view = (int) redisService.getValueByHashKey(RedisKey.BLOG_VIEWS_MAP, blog.getId());
            blog.setViews(view);
        }
    }

    private void processBlog(Blog blog){
        if (blog==null) return;
        // 添加标签
        blog.setTags(tagService.getTagByBlogId(blog.getId()));
        // 将正文变为html
        blog.setContent(MarkDownToHTMLUtil.markdownToHtml(blog.getContent()));
        // 将描述转为html
        blog.setDescription(MarkDownToHTMLUtil.markdownToHtml(blog.getDescription()));
        // 设置最新的view
        int view = (int) redisService.getValueByHashKey(RedisKey.BLOG_VIEWS_MAP, blog.getId());
        blog.setViews(view);
    }

    private void processBlogListItem(List<BlogListItem> blogs){
        if (CollectionUtils.isEmpty(blogs)) return;
        for (BlogListItem blog : blogs) {
            if (blog==null) continue;
        // 添加标签
        blog.setTags(tagService.getTagByBlogId(blog.getId()));
        // 将描述转为html
        blog.setDescription(MarkDownToHTMLUtil.markdownToHtml(blog.getDescription()));
        // 设置最新的view
        int view = (int) redisService.getValueByHashKey(RedisKey.BLOG_VIEWS_MAP, blog.getId());
        blog.setViews(view);
        }
    }

    @Transactional
    @Override
    public String saveBlog(Blog blog) {
        // 判断是否有空值
        checkBLog(blog);

        // 判断博客category是否是新添加的
        blog.setCategory(categoryService.addNewBlogCategory(blog.getCategory()));

        // 如果tag不存在，就新建tag,并返回带有主键的tags
        List<Tag> tags = tagService.saveNewBlogTags(blog.getTags());

        // 设置更新时间
        blog.setUpdateTime(new Date());
        // 如果是新建
        if (blogMapper.getBlogById(blog.getId())==null){
            blog.setCreateTime(new Date());
            blogMapper.saveBlog(blog);
        }else {
            blogMapper.updateBlogById(blog);
        }

        Long blogId = blog.getId();

        // 维护blog-tag表
        blogMapper.deleteBlogTag(blogId);
        for (Tag tag:tags){
            blogMapper.saveBlogTag(blogId,tag.getTagId());
        }

        // 在Redis View Map中添加对应的view id
        redisService.saveKVToHash(RedisKey.BLOG_VIEWS_MAP,blog.getId(),0);
        // 删除原有缓存
        deleteBlogRedisCache();

        return blog.getTitle();
    }

    /**
     * 检查blog
     * @author huangshuai
     * @Date 2021/12/29 14:39
     * @param blog 博客实体
     */
    private void checkBLog(Blog blog){
        if (StringUtils.isEmpty(blog.getTitle(),blog.getContent(),
                blog.getDescription())||blog.getWords()<0){
            throw ServiceException.create("博客信息有误，正文或者标题为空！");
        }
        if (blog.getCategory()==null){
            throw ServiceException.create("博客分类不能为空！");
        }
        if (CollectionUtils.isEmpty(blog.getTags())){
            throw ServiceException.create("博客标签不能为空！");
        }
    }

    @Override
    public PageInfo<BlogListItem> getBlogByTag(Tag tag,Integer pageNum) {
        Tag blogTag = tagService.getTagByIdOrName(tag);
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<BlogListItem> blogs = blogMapper.getBlogByTagId(blogTag.getTagId());
        processBlogListItem(blogs);
        return new PageInfo<>(blogs);
    }

    @Override
    public PageInfo<Blog> getBlogByCategory(Category category, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<Blog> blogs = blogMapper.getBlogByCategory(category);
        processBlog(blogs);
        return new PageInfo<>(blogs);
    }

    @Override
    public Map<String,Object> getArchiveBlogIsPublished(){
        String redisKey = RedisKey.ARCHIVE_BLOG_MAP;
        // 从Redis中获取archiveMap
        Map<String, Object> mapFromRedis = redisService.getMapByKey(redisKey);
        if (mapFromRedis != null) {
            return mapFromRedis;
        }

        Map<String, Object> map = new HashMap<>();
        List<String> groupYearMonth = blogMapper.getGroupYearMonthByIsPublished();
        Map<String, List<ArchiveBlog>> archiveBlogMap = new LinkedHashMap<>();
        int count=0;
        for (String s : groupYearMonth) {
            List<ArchiveBlog> archiveBlogs = blogMapper.getArchiveBlogListByYearMonthAndIsPublished(s);
            count+=archiveBlogs.size();
            archiveBlogMap.put(s, archiveBlogs);
        }
        map.put("blogMap", archiveBlogMap);
        map.put("count", count);

        // 向Redis中放入archiveMap
        redisService.saveMapToValue(redisKey, map);
        return map;
    }

    @Override
    public List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend() {
        return blogMapper.getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend(randomBlogLimitNum);
    }

    @Transactional
    @Override
    public void deleteBlogById(Long id) {
        // 删除blog
        blogMapper.deleteBlogById(id);
        // 维护blog_tag表
        blogMapper.deleteBlogTag(id);
        // 清除缓存
        deleteBlogRedisCache();
        // 删除view hash中本博客对应的hashkey及value
        redisService.deleteByHashKey(RedisKey.BLOG_VIEWS_MAP, id);
    }

    @Override
    public List<Blog> getBlogListByTitleAndCategoryId(String title, Long categoryId) {
        return blogMapper.getBlogListByTitleAndCategoryId(title,categoryId);
    }

    @Override
    public void updateBlogTopById(Long id, Boolean top) {
        blogMapper.updateBlogTopById(id,top);
        redisService.deleteCacheByKey(RedisKey.HOME_BLOG_INFO_LIST);
    }

    @Override
    public void updateBlogPublishedById(Long id, Boolean published) {
        blogMapper.updateBlogPublishedById(id, published);

        // 删除Redis缓存
        deleteBlogRedisCache();
    }

    @Override
    public String getBlogTitleById(Long id) {
        return blogMapper.getTitleById(id);
    }

    @Override
    public Boolean getCommentEnabledByBlogId(Long id) {
        return blogMapper.getCommentEnabledByBlogId(id);
    }

    @Override
    public Boolean getPublishedByBlogId(Long id) {
        return blogMapper.getPublishedByBlogId(id);
    }

    @Override
    public List<BlogIdAndTitle> getIdAndTitleList() {
        return blogMapper.getIdAndTitleList();
    }

    @Override
    public List<BlogIdAndTitle> getNewBlogListByIsPublished() {
        String redisKey = RedisKey.NEW_BLOG_LIST;
        List<BlogIdAndTitle> newBlogListFromRedis = redisService.getListByValue(redisKey);
        if (newBlogListFromRedis != null) {
            return newBlogListFromRedis;
        }
        PageHelper.startPage(1, newBlogPageSize);
        List<BlogIdAndTitle> newBlogList = blogMapper.getIdAndTitleList();
        redisService.saveListToValue(redisKey, newBlogList);
        return newBlogList;
    }

    /**
     * 删除首页缓存、最新推荐缓存、归档页面缓存、博客浏览量缓存
     */
    private void deleteBlogRedisCache() {
        redisService.deleteCacheByKey(RedisKey.HOME_BLOG_INFO_LIST);
        redisService.deleteCacheByKey(RedisKey.ARCHIVE_BLOG_MAP);
    }

    @Transactional
    @Override
    public void updateViews(Long blogId, Integer views) {
        if (blogMapper.updateViews(blogId, views) != 1) {
            throw new PersistenceException("更新失败");
        }
    }
}
