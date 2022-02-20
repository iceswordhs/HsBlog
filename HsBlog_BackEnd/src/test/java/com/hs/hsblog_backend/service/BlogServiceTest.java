package com.hs.hsblog_backend.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.entity.Blog;
import com.hs.hsblog_backend.model.vo.BlogListItem;
import com.hs.hsblog_backend.repository.BlogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/12/27 14:20
 */
@SpringBootTest
public class BlogServiceTest {
    @Autowired
    BlogService blogService;

    @Autowired
    BlogMapper blogMapper;

    @Test
    void getPageBlog(){
        PageInfo<BlogListItem> pageBlog = blogService.getPageBlogIsPublished(2);
        // 获取总页数 6
        System.out.println(pageBlog.getPages());
        // 获取当前页数 2
        System.out.println(pageBlog.getPageNum());
        System.out.println(pageBlog.getNavigatePages());
        System.out.println(pageBlog.getList().get(0));
        System.out.println(pageBlog.getList().get(1));
        System.out.println(pageBlog);
    }

    @Test
    void testPageHelper(){
        PageHelper.startPage(2,5);
        List<BlogListItem> allBlog = blogMapper.findAllBlog();
        System.out.println(allBlog.get(0));
    }

    @Test
    void testRandomBlog(){
        System.out.println(blogService.getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend());
    }
}
