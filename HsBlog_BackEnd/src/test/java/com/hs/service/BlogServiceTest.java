package com.hs.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.entity.Blog;
import com.hs.model.vo.BlogIdAndTitle;
import com.hs.model.vo.BlogListItem;
import com.hs.repository.BlogMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/12/27 14:20
 */
@SpringBootTest
@Transactional
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

    @Test
    void testNewBlogListByIsPublished(){
        List<BlogIdAndTitle> blogs = blogService.getNewBlogListByIsPublished();
        for (BlogIdAndTitle blog : blogs) {
            System.out.println(blog.getId()+blog.getTitle());
        }
    }

    @Test
    @Disabled
    void testGetBlogById(){
        System.out.println(blogMapper.getBlogById(50L));
    }

    @Test
    void testManageBlog(){
        String orderBy = "create_time desc";
        PageHelper.startPage(1, 10, orderBy);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogService.getBlogListByTitleAndCategoryId("",null));
        System.out.println(pageInfo);
    }
}
