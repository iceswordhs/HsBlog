package com.hs.hsblog_backend.service;

import com.hs.hsblog_backend.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/12/27 14:43
 */
@SpringBootTest
public class TagServiceTest {
    @Autowired
    TagService tagService;

    @Test
    void getTagByBlogId(){
        List<Tag> tagByBlogId = tagService.getTagByBlogId(1);
        System.out.println(tagByBlogId);
    }
}
