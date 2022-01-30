package com.hs.hsblog_backend.service;

import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.entity.Blog;
import com.hs.hsblog_backend.entity.Category;
import com.hs.hsblog_backend.entity.Tag;
import com.hs.hsblog_backend.model.vo.ArchiveBlog;
import com.hs.hsblog_backend.model.vo.BlogListItem;

import java.util.List;
import java.util.Map;

/**
 * @author Hs
 * @Date 2021/12/3 20:08
 */
public interface BlogService {
    PageInfo<BlogListItem> getAllBlog();

    Blog getBlogById(Integer id);

    Blog getBlogEditById(Integer id);

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

    void deleteBlogById(Integer id);

    List<Blog> getBlogListByTitleAndCategoryId(String title, Integer categoryId);

    void updateBlogTopById(Integer id,Boolean top);

    void updateBlogPublishedById(Integer id,Boolean published);
}
