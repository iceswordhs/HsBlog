package com.hs.hsblog_backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.constants.exception.ServiceException;
import com.hs.hsblog_backend.entity.Blog;
import com.hs.hsblog_backend.entity.Category;
import com.hs.hsblog_backend.entity.Tag;
import com.hs.hsblog_backend.model.vo.ArchiveBlog;
import com.hs.hsblog_backend.model.vo.BlogListItem;
import com.hs.hsblog_backend.repository.BlogMapper;
import com.hs.hsblog_backend.service.BlogService;
import com.hs.hsblog_backend.service.CategoryService;
import com.hs.hsblog_backend.service.TagService;
import com.hs.hsblog_backend.util.StringUtils;
import com.hs.hsblog_backend.util.commarkUtil.MarkDownToHTMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    // 博客简介列表排序方式
    private static final String orderBy = "top desc, create_time desc";
    private static final Integer pageSize=5;

    @Override
    public PageInfo<BlogListItem> getAllBlog() {
        List<BlogListItem> allBlog = blogMapper.findAllBlog();
        return new PageInfo<>(allBlog);
    }

    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = blogMapper.getBlogById(id);
        processBlog(blog);
        return blog;
    }

    @Override
    public Blog getBlogEditById(Integer id) {
        Blog blog = blogMapper.getBlogEditById(id);
        // 添加标签
        blog.setTags(tagService.getTagByBlogId(blog.getId()));
        return blog;
    }

    @Override
    public PageInfo<BlogListItem> getPageBlog(int pageNum){
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<BlogListItem> blogs = blogMapper.findAllBlog();
        processBlogListItem(blogs);
        return new PageInfo<>(blogs);
    }

    /**
     * 在返回博客时，对博客进行处理 添加标签，转为html
     * @author huangshuai
     * @Date 2021/12/29 14:36
     * @param blogs 要处理的博客
     */
    private void processBlog(List<Blog> blogs){
        for (Blog blog : blogs) {
            // 添加标签
            blog.setTags(tagService.getTagByBlogId(blog.getId()));
            // 将正文变为html
            if (!StringUtils.isEmpty(blog.getContent()))
            blog.setContent(MarkDownToHTMLUtil.markdownToHtml(blog.getContent()));
            // 将描述转为html
            if(!StringUtils.isEmpty(blog.getDescription()))
            blog.setDescription(MarkDownToHTMLUtil.markdownToHtml(blog.getDescription()));
        }
    }

    private void processBlog(Blog blog){
        // 添加标签
        blog.setTags(tagService.getTagByBlogId(blog.getId()));
        // 将正文变为html
        blog.setContent(MarkDownToHTMLUtil.markdownToHtml(blog.getContent()));
        // 将描述转为html
        blog.setDescription(MarkDownToHTMLUtil.markdownToHtml(blog.getDescription()));
    }

    private void processBlogListItem(List<BlogListItem> blogs){
        for (BlogListItem blog : blogs) {
        // 添加标签
        blog.setTags(tagService.getTagByBlogId(blog.getId()));
        // 将描述转为html
        blog.setDescription(MarkDownToHTMLUtil.markdownToHtml(blog.getDescription()));
        }
    }

    @Override
    public String saveBlog(Blog blog) {
        // 判断是否有空值
        checkBLog(blog);

        // 判断博客category是否是新添加的
        blog.setCategory(categoryService.addNewBlogCategory(blog.getCategory()));

        // 如果tag不存在，就新建tag,并返回带有主键的tags
        List<Tag> tags = tagService.saveNewBlogTags(blog.getTags());

        // 设置更新时间
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blogMapper.saveBlog(blog);
        Integer blogId = blog.getId();

        // 维护blog-tag表
        blogMapper.deleteBlogTag(blogId);
        for (Tag tag:tags){
            blogMapper.saveBlogTag(blogId,tag.getTagId());
        }

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
        return map;
    }

    @Override
    public void deleteBlogById(Integer id) {
        // 删除blog
        blogMapper.deleteBlogTag(id);
        // 维护blog_tag表
        blogMapper.deleteBlogTag(id);
    }

    @Override
    public List<Blog> getBlogListByTitleAndCategoryId(String title, Integer categoryId) {
        return blogMapper.getBlogListByTitleAndCategoryId(title,categoryId);
    }

    @Override
    public void updateBlogTopById(Integer id, Boolean top) {
        blogMapper.updateBlogTopById(id,top);
    }

    @Override
    public void updateBlogPublishedById(Integer id, Boolean published) {
        blogMapper.updateBlogPublishedById(id, published);
    }
}
