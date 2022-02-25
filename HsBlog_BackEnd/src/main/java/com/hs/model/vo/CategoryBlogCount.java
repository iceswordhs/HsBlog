package com.hs.model.vo;

/**
 * @author Hs
 * @Date 2022/2/20 21:09
 */
public class CategoryBlogCount {
    private Long id;
    private String name;//分类名
    private Integer value;//分类下博客数量

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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CategoryBlogCount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
