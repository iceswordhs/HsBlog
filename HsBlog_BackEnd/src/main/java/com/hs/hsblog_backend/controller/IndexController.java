package com.hs.hsblog_backend.controller;

import com.hs.hsblog_backend.util.Result;
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
    //@GetMapping("/site")
    //public Result<Map<String,Object>> site(){
    //    Map<String, Object> map = siteSettingService.getSiteInfo();
    //    List<NewBlog> newBlogList = blogService.getNewBlogListByIsPublished();
    //    List<Category> categoryList = categoryService.getCategoryNameList();
    //    List<Tag> tagList = tagService.getTagListNotId();
    //    List<RandomBlog> randomBlogList = blogService.getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();
    //    map.put("newBlogList", newBlogList);
    //    map.put("categoryList", categoryList);
    //    map.put("tagList", tagList);
    //    map.put("randomBlogList", randomBlogList);
    //    return Result.ok("请求成功", map);
    //}
}
