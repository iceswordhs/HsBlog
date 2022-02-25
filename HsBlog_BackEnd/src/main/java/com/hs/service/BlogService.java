package com.hs.service;

import com.github.pagehelper.PageInfo;
import com.hs.entity.Blog;
import com.hs.entity.Category;
import com.hs.entity.Tag;
import com.hs.model.vo.*;
import com.hs.model.vo.BlogIdAndTitle;
import com.hs.model.vo.BlogListItem;
import com.hs.model.vo.RandomBlog;
import com.hs.model.vo.SearchBlog;

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

    Blog getBlogEditById(Long id,Boolean admin);

    PageInfo<BlogListItem> getPageBlogIsPublished(int pageNum);

    String saveBlog(Blog blog);

    PageInfo<BlogListItem> getBlogByTag(Tag tag, Integer pageNum);

    PageInfo<BlogListItem> getBlogByCategory(Category category, Integer pageNum);

    /**
     * 获取所有公开的blog的标题及id及创建时间
     * @author huangshuai
     * @Date 2022/1/7 20:42
     * @return java.util.List<com.hs.entity.Blog>
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
