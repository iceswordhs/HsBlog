package com.hs.hsblog_backend.repository;

import com.hs.hsblog_backend.entity.Blog;
import com.hs.hsblog_backend.entity.Category;
import com.hs.hsblog_backend.model.dto.BlogView;
import com.hs.hsblog_backend.model.vo.ArchiveBlog;
import com.hs.hsblog_backend.model.vo.BlogIdAndTitle;
import com.hs.hsblog_backend.model.vo.BlogListItem;
import com.hs.hsblog_backend.model.vo.SearchBlog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.hsblog_backend.entity.Blog
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

    List<BlogListItem> getBlogByTagId(Long tagId);

    List<Blog> getBlogByCategory(Category category);

    List<String> getGroupYearMonthByIsPublished();

    List<ArchiveBlog> getArchiveBlogListByYearMonthAndIsPublished(String yearMonth);

    List<Blog> getBlogListByTitleAndCategoryId(String title, Long categoryId);

    void updateBlogTopById(Long id, Boolean top);

    void updateBlogPublishedById(Long id,Boolean published);

    List<BlogView> getBlogViewsList();

    int updateViews(Long id, Integer views);

    String getTitleById(Long id);

    Boolean getCommentEnabledByBlogId(Long id);

    Boolean getPublishedByBlogId(Long id);

    List<BlogIdAndTitle> getIdAndTitleList();

    void updateBlogById(Blog blog);
}




