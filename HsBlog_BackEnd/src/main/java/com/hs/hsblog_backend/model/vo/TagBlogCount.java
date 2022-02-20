package com.hs.hsblog_backend.model.vo;

/**
 * @author Hs
 * @Date 2022/2/20 21:09
 */
public class TagBlogCount {
    private Long id;
    private String name;//标签名
    private Integer value;//标签下博客数量

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
        return "TagBlogCount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
