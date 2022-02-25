package com.hs.repository;

import com.github.pagehelper.PageHelper;
import com.hs.model.vo.BlogListItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/12/3 16:48
 */
@SpringBootTest
public class BlogMapperTest {
    @Autowired
    BlogMapper blogMapper;

    @Test
    void testFindAllBlog(){
        List<BlogListItem> allBlog = blogMapper.findAllBlog();
        System.out.println(allBlog);
    }

    @Test
    void testPageHelper(){
        PageHelper.startPage(1,3);
        List<BlogListItem> allBlog = blogMapper.findAllBlog();
        System.out.println(allBlog.get(0));
    }
}
