package com.hs.hsblog_backend.service;

import com.hs.hsblog_backend.entity.Category;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/12/16 17:19
 */
public interface CategoryService {
    List<Category> getAllCategories();

    Category findCategoryByName(String categoryName);

    void addCategory(Category category);

    void deleteCategoryById(Integer id);

    void updateCategory(Category category);

    Category addNewBlogCategory(Category category);
}
