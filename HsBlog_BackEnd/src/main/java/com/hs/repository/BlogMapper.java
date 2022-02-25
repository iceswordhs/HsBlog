package com.hs.repository;

import com.hs.entity.Blog;
import com.hs.entity.Category;
import com.hs.model.dto.BlogView;
import com.hs.model.vo.*;
import com.hs.model.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.entity.Blog
 */
@Mapper
public interface BlogMapper {
    List<BlogListItem> findAllBlog();

    List<BlogListItem> findAllPublishedBlog();

    List<SearchBlog> getSearchBlogListIsPublished(String query);

    Blog getBlogById(Long id);

    Blog getBlogEditById(Long id);

    Integer saveBlog(Blog blog);

    void saveBlogTag(Long blogId,Long tagId);

    void deleteBlogTag(Long blogId);

    void deleteBlogById(Long blogId);

    List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend(Integer limitNum);

    List<BlogListItem> getBlogByTagId(Long tagId);

    List<BlogListItem> getBlogByCategory(Category category);

    List<String> getGroupYearMonthByIsPublished();

    List<ArchiveBlog> getArchiveBlogListByYearMonthAndIsPublished(String yearMonth);

    List<Blog> getBlogListByTitleAndCategoryId(String title, Long categoryId);

    void updateBlogTopById(Long id, Boolean top);

    void updateBlogPublishedById(Long id,Boolean published);

    void updateBlogRecommendById(Long id, Boolean recommend);

    List<BlogView> getBlogViewsList();

    int updateViews(Long id, Integer views);

    String getTitleById(Long id);

    Boolean getCommentEnabledByBlogId(Long id);

    Boolean getPublishedByBlogId(Long id);

    List<BlogIdAndTitle> getIdAndTitleList();

    void updateBlogById(Blog blog);

    List<CategoryBlogCount> getCategoryBlogCountList();

    Integer countBlog();
}




