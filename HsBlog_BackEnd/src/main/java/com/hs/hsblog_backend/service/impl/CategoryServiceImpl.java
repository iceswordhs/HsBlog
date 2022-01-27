package com.hs.hsblog_backend.service.impl;

import com.hs.hsblog_backend.entity.Category;
import com.hs.hsblog_backend.repository.CategoryMapper;
import com.hs.hsblog_backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/12/16 17:32
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.getAllCategories();
    }

    @Override
    public Category findCategoryByName(String categoryName) {
        return categoryMapper.findCategoryByName(categoryName);
    }

    @Override
    public void addCategory(Category category) {
        categoryMapper.addCategory(category);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryMapper.deleteCategoryById(id);
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategoryById(category);
    }
}
