package com.hs.hsblog_backend.controller;

import com.hs.hsblog_backend.annotation.VisitLogger;
import com.hs.hsblog_backend.entity.Blog;
import com.hs.hsblog_backend.service.BlogService;
import com.hs.hsblog_backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hs
 * @Date 2022/1/7 20:39
 */
@RestController
public class ArchiveController {
    @Autowired
    BlogService blogService;

    @VisitLogger(behavior = "访问页面", content = "文章归档")
    @GetMapping("/archive")
    public Result<Map<String, Object>> getArchive(){
        Map<String, Object> archiveBlogs = blogService.getArchiveBlogIsPublished();
        return Result.success(archiveBlogs);
    }
}
