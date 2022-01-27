package com.hs.hsblog_backend.model.vo;

/**
 * @author Hs
 * @Date 2022/1/13 10:47
 */
public class ArchiveBlog {
    private Long id;
    private String title;
    private String day;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "ArchiveBlog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
