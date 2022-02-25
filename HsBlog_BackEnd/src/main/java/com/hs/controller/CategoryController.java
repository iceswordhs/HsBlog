package com.hs.controller;

import com.github.pagehelper.PageInfo;
import com.hs.annotation.VisitLogger;
import com.hs.entity.Category;
import com.hs.model.vo.BlogListItem;
import com.hs.service.BlogService;
import com.hs.service.CategoryService;
import com.hs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/12/17 9:40
 */
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    BlogService blogService;


    @GetMapping("/getAllCategories")
    public Result<List<Category>> getAllCategory(){
        List<Category> categories = categoryService.getAllCategories();
        return Result.success(categories);
    }

    @VisitLogger(behavior = "查看分类下的所有博客")
    @GetMapping("/category")
    public Result<PageInfo<BlogListItem>> getBLogByCategory(String categoryName,@RequestParam(defaultValue = "1") Integer pageNum){
        Category categoryByName = categoryService.findCategoryByName(categoryName);
        PageInfo<BlogListItem> blogs = blogService.getBlogByCategory(categoryByName, pageNum);
        return Result.success(blogs);
    }
}
