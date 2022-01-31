package com.hs.hsblog_backend.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 动态
 * @TableName moment
 */
public class Moment implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private Date createTime;

    /**
     * 点赞数
     */
    private Integer likes;

    /**
     * 
     */
    private Boolean published;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 点赞数
     */
    public Integer getLikes() {
        return likes;
    }

    /**
     * 点赞数
     */
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    /**
     * 
     */
    public Boolean getPublished() {
        return published;
    }

    /**
     * 
     */
    public void setPublished(Boolean published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "Moment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", likes=" + likes +
                ", published=" + published +
                '}';
    }
}