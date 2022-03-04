package com.hs.service;

import com.github.pagehelper.PageInfo;
import com.hs.entity.Category;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/12/16 17:19
 */
public interface CategoryService {
    List<Category> getAllCategories();

    PageInfo<Category> getCategoriesByPage(Integer pageNum, Integer pageSize);

    Category findCategoryByName(String categoryName);

    void addCategory(Category category);

    void deleteCategoryById(Long id);

    void updateCategory(Category category);

    Category addNewBlogCategory(Category category);
}
