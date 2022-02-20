package com.hs.hsblog_backend.controller;

import com.hs.hsblog_backend.entity.Category;
import com.hs.hsblog_backend.entity.Tag;
import com.hs.hsblog_backend.model.vo.BlogIdAndTitle;
import com.hs.hsblog_backend.model.vo.RandomBlog;
import com.hs.hsblog_backend.service.BlogService;
import com.hs.hsblog_backend.service.CategoryService;
import com.hs.hsblog_backend.service.SiteSettingService;
import com.hs.hsblog_backend.service.TagService;
import com.hs.hsblog_backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Hs
 * @Date 2022/1/6 17:23
 */
@RestController
public class IndexController {
    @Autowired
    SiteSettingService siteSettingService;
    @Autowired
    BlogService blogService;
    @Autowired
    TagService tagService;

    @GetMapping("/site")
    public Result<Map<String,Object>> site(){
        Map<String, Object> map = siteSettingService.getSite();
        List<BlogIdAndTitle> newBlogList = blogService.getNewBlogListByIsPublished();
        List<Tag> tagList = tagService.getAllTags();
        List<RandomBlog> randomBlogList = blogService.getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();
        map.put("newBlogList", newBlogList);
        map.put("tagList", tagList);
        map.put("randomBlogList", randomBlogList);
        return Result.success(map,"请求成功");
    }
}
