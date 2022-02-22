package com.hs.hsblog_backend.service.impl;

import com.hs.hsblog_backend.constants.RedisKey;
import com.hs.hsblog_backend.entity.Category;
import com.hs.hsblog_backend.repository.CategoryMapper;
import com.hs.hsblog_backend.service.CategoryService;
import com.hs.hsblog_backend.service.RedisService;
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
    @Autowired
    RedisService redisService;

    @Override
    public List<Category> getAllCategories() {
        String redisKey= RedisKey.CATEGORY_NAME_LIST;
        List<Category> categoryList = redisService.getListByValue(redisKey);
        if (categoryList!=null){
            return categoryList;
        }
        List<Category> categories = categoryMapper.getAllCategories();
        redisService.saveListToValue(redisKey,categories);
        return categories;
    }

    @Override
    public Category findCategoryByName(String categoryName) {
        return categoryMapper.findCategoryByName(categoryName);
    }

    @Override
    public void addCategory(Category category) {
        categoryMapper.addCategory(category);
        redisService.deleteCacheByKey(RedisKey.CATEGORY_NAME_LIST);
    }

    @Override
    public Category addNewBlogCategory(Category category) {
        Category categoryByName = findCategoryByName(category.getName());
        if(categoryByName==null){
            addCategory(category);
            return findCategoryByName(category.getName());
        }else {
            return categoryByName;
        }
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryMapper.deleteCategoryById(id);
        redisService.deleteCacheByKey(RedisKey.CATEGORY_NAME_LIST);
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategoryById(category);
        redisService.deleteCacheByKey(RedisKey.CATEGORY_NAME_LIST);
        redisService.deleteCacheByKey(RedisKey.HOME_BLOG_INFO_LIST);
    }
}
