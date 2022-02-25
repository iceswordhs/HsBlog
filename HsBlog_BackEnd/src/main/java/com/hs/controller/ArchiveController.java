package com.hs.controller;

import com.hs.annotation.VisitLogger;
import com.hs.service.BlogService;
import com.hs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
