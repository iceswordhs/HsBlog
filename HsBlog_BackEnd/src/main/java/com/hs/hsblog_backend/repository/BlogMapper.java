package com.hs.hsblog_backend.repository;

import com.hs.hsblog_backend.entity.Blog;
import com.hs.hsblog_backend.entity.Category;
import com.hs.hsblog_backend.model.vo.ArchiveBlog;
import com.hs.hsblog_backend.model.vo.BlogListItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.hsblog_backend.entity.Blog
 */
@Mapper
public interface BlogMapper {
    List<BlogListItem> findAllBlog();

    Blog getBlogById(Integer id);

    Integer saveBlog(Blog blog);

    void saveBlogTag(Integer blogId,Integer tagId);

    void deleteBlogTag(Integer blogId);

    List<BlogListItem> getBlogByTagId(Integer tagId);

    List<Blog> getBlogByCategory(Category category);

    List<String> getGroupYearMonthByIsPublished();

    List<ArchiveBlog> getArchiveBlogListByYearMonthAndIsPublished(String yearMonth);

    List<Blog> getBlogListByTitleAndCategoryId(String title, Integer categoryId);

    void updateBlogTopById(Integer id, Boolean top);

    void updateBlogPublishedById(Integer id,Boolean published);
}




