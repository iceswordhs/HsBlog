package com.hs.hsblog_backend.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.entity.Category;
import com.hs.hsblog_backend.entity.NullObject;
import com.hs.hsblog_backend.service.CategoryService;
import com.hs.hsblog_backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/12/29 20:35
 */
@RestController
@RequestMapping("/admin")
public class CategoryAdminController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/getAllCategories")
    public Result<List<Category>> getAllCategories(){
        List<Category> allCategory = categoryService.getAllCategories();
        return Result.success(allCategory);
    }

    @GetMapping("/getCategoriesByPage")
    public Result<PageInfo<Category>> getCategoriesByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                                      @RequestParam(defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Category> allCategory = categoryService.getAllCategories();
        PageInfo<Category> categoryPageInfo = new PageInfo<>(allCategory);
        return Result.success(categoryPageInfo);
    }

    @PostMapping("/addCategory")
    public Result<NullObject> addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        return Result.success();
    }

    @DeleteMapping("/deleteCategory")
    public Result<NullObject> deleteCategory(@RequestParam Integer id){
        categoryService.deleteCategoryById(id);
        return Result.success();
    }

    @PostMapping("/updateCategory")
    public Result<NullObject> updateCategory(@RequestBody Category category){
        categoryService.updateCategory(category);
        return Result.success();
    }
}
