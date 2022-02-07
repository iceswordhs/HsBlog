package com.hs.hsblog_backend.controller;

import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.annotation.VisitLogger;
import com.hs.hsblog_backend.entity.Blog;
import com.hs.hsblog_backend.entity.Tag;
import com.hs.hsblog_backend.model.vo.BlogListItem;
import com.hs.hsblog_backend.service.BlogService;
import com.hs.hsblog_backend.service.TagService;
import com.hs.hsblog_backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/12/17 9:38
 */
@RestController
public class TagController {
    @Autowired
    TagService tagService;
    @Autowired
    BlogService blogService;

    @VisitLogger(behavior = "查看标签下的所有博客")
    @GetMapping("/tag")
    public Result<PageInfo<BlogListItem>> getAllTags(@RequestParam String tagName, @RequestParam(defaultValue = "1") Integer pageNum){
        PageInfo<BlogListItem> blogs = blogService.getBlogByTag(new Tag(tagName),pageNum);
        return Result.success(blogs);
    }

    @GetMapping("/getAllTags")
    public Result<List<Tag>> getAllTags(){
        List<Tag> allTags = tagService.getAllTags();
        return Result.success(allTags);
    }
}
