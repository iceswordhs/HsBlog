package com.hs.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章分类
 * @author Hs
 * @Date 2021/12/3 14:14
 */
public class Category {
    private Long id;
    private String name;//分类名称
    private List<Blog> blogs = new ArrayList<>();//该分类下的博客文章

    public Category(){
    }

    public Category(Long id){
        this.id=id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogs=" + blogs +
                '}';
    }
}
