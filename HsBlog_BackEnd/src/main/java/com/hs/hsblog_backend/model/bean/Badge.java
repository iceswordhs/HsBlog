package com.hs.hsblog_backend.model.bean;

/**
 * @author Hs
 * @Date 2022/1/25 22:53
 */
public class Badge {
    private String title;
    private String url;
    private String subject;
    private String value;
    private String color;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Badge{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", subject='" + subject + '\'' +
                ", value='" + value + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
