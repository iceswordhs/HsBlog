package com.hs.service;

import com.hs.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * @author Hs
 * @Date 2021/12/27 14:43
 */
@SpringBootTest
@Transactional
public class TagServiceTest {
    @Autowired
    TagService tagService;

    @Test
    void getTagByBlogId(){
        List<Tag> tagByBlogId = tagService.getTagByBlogId(1L);
        System.out.println(tagByBlogId);
    }

    //@Test
    void addTag(){
        Tag tag = new Tag("测试标签");
        tagService.addTag(tag);
        System.out.println(tag);
    }

    @Test
    void random(){
        for (int i=0;i<10;i++){
            Random random = new Random();
            int colorRandomInt = random.nextInt(8);
            System.out.println(colorRandomInt);
        }
    }
}
