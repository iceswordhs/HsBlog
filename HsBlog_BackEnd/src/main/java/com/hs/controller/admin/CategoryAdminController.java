package com.hs.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.annotation.OperationAnnotation;
import com.hs.entity.Category;
import com.hs.entity.NullObject;
import com.hs.service.CategoryService;
import com.hs.util.Result;
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
        PageInfo<Category> categoryPageInfo = categoryService.getCategoriesByPage(pageNum,pageSize);
        return Result.success(categoryPageInfo);
    }

    @OperationAnnotation("添加分类")
    @PostMapping("/addCategory")
    public Result<NullObject> addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        return Result.success();
    }

    @OperationAnnotation("根据Id删除分类")
    @DeleteMapping("/deleteCategory")
    public Result<NullObject> deleteCategory(@RequestParam Long id){
        categoryService.deleteCategoryById(id);
        return Result.success();
    }

    @OperationAnnotation("更新分类")
    @PostMapping("/updateCategory")
    public Result<NullObject> updateCategory(@RequestBody Category category){
        categoryService.updateCategory(category);
        return Result.success();
    }
}
