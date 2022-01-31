package com.hs.hsblog_backend.model.dto;

/**
 * @author Hs
 * @Date 2022/1/30 18:56
 */
public class BlogView {
    private Long id;
    private Integer views;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "BlogView{" +
                "id=" + id +
                ", views=" + views +
                '}';
    }
}
