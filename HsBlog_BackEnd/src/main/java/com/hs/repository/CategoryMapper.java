package com.hs.repository;

import com.hs.entity.Blog;
import com.hs.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/12/16 17:19
 */
@Mapper
public interface CategoryMapper {
    List<Category> getAllCategories();

    Category findCategoryByName(String categoryName);

    List<Blog> getBlogByCategoryName(String categoryName);

    void addCategory(Category category);

    void deleteCategoryById(Long id);

    void updateCategoryById(Category category);
}
