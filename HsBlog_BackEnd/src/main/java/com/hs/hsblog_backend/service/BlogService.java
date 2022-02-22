package com.hs.hsblog_backend.service;

import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.entity.Blog;
import com.hs.hsblog_backend.entity.Category;
import com.hs.hsblog_backend.entity.Tag;
import com.hs.hsblog_backend.model.vo.*;

import java.util.List;
import java.util.Map;

/**
 * @author Hs
 * @Date 2021/12/3 20:08
 */
public interface BlogService {
    PageInfo<BlogListItem> getAllBlog();

    List<SearchBlog> getSearchBlogListIsPublished(String query);

    Blog getBlogById(Long id);

    Blog getBlogEditById(Long id);

    PageInfo<BlogListItem> getPageBlogIsPublished(int pageNum);

    String saveBlog(Blog blog);

    PageInfo<BlogListItem> getBlogByTag(Tag tag,Integer pageNum);

    PageInfo<Blog> getBlogByCategory(Category category, Integer pageNum);

    /**
     * 获取所有公开的blog的标题及id及创建时间
     * @author huangshuai
     * @Date 2022/1/7 20:42
     * @return java.util.List<com.hs.hsblog_backend.entity.Blog>
     */
    Map<String,Object> getArchiveBlogIsPublished();

    List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();

    void deleteBlogById(Long id);

    List<Blog> getBlogListByTitleAndCategoryId(String title, Long categoryId);

    void updateBlogTopById(Long id,Boolean top);

    void updateBlogPublishedById(Long id,Boolean published);

    void updateBlogRecommendById(Long id,Boolean recommend);

    String getBlogTitleById(Long id);

    Boolean getCommentEnabledByBlogId(Long id);

    Boolean getPublishedByBlogId(Long id);

    List<BlogIdAndTitle> getIdAndTitleList();

    List<BlogIdAndTitle> getNewBlogListByIsPublished();

    // Redis
    void updateViewsToRedis(Long blogId);

    // 将view保存到数据库
    void updateViews(Long blogId, Integer views);
}
