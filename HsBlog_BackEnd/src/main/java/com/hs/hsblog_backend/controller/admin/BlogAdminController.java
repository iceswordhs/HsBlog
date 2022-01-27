package com.hs.hsblog_backend.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.entity.Blog;
import com.hs.hsblog_backend.entity.Category;
import com.hs.hsblog_backend.entity.NullObject;
import com.hs.hsblog_backend.service.BlogService;
import com.hs.hsblog_backend.service.CategoryService;
import com.hs.hsblog_backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hs
 * @Date 2021/12/11 17:32
 */
@RestController
@RequestMapping("/admin")
public class BlogAdminController {
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;

    /**
     * 保存博客  前端传入 文章标题、分类、标签、描述、公开或私密、正文、文章展示图
     * 返回标题
     * @author huangshuai
     * @Date 2021/12/14 14:09
     * @param blog
     * @return com.hs.hsblog_backend.util.Result<String>
     */
    @PostMapping("/saveBlog")
    public Result<String> saveBlog(@RequestBody Blog blog){
        String blogTitle = blogService.saveBlog(blog);
        return Result.success(blogTitle);
    }

    @GetMapping("/blog")
    public Result<Blog> getBlogById(@RequestParam Integer id){
        Blog blogById = blogService.getBlogById(id);
        return Result.success(blogById);
    }

    @PostMapping("/deleteBlogById")
    public Result<NullObject> deleteBlogById(@RequestParam Integer id){
        blogService.deleteBlogById(id);
        return Result.success();
    }

    @GetMapping("/manageBlog")
    public Result<Map<String,Object>> manageBlog(@RequestParam(defaultValue = "") String title,
                                                 @RequestParam(defaultValue = "") Integer categoryId,
                                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                                 @RequestParam(defaultValue = "10") Integer pageSize){
        String orderBy = "create_time desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogService.getBlogListByTitleAndCategoryId(title, categoryId));
        List<Category> categories = categoryService.getAllCategories();
        Map<String, Object> map = new HashMap<>();
        map.put("blogs", pageInfo);
        map.put("categories", categories);
        return Result.success(map);
    }

    @PostMapping("/updateBlogTop")
    public Result<NullObject> updateBlogTop(@RequestParam Integer id, @RequestParam Boolean top){
        blogService.updateBlogTopById(id, top);
        return Result.success();
    }

    @PostMapping("/updateBlogPublished")
    public Result<NullObject> updateBlogPublished(@RequestParam Integer id, @RequestParam Boolean published){
        blogService.updateBlogPublishedById(id, published);
        return Result.success();
    }
}
