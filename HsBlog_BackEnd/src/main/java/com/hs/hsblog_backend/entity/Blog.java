package com.hs.hsblog_backend.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @TableName blog
 */
public class Blog implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客展示图
     */
    private String homePicture;

    /**
     * 描述
     */
    private String description;

    /**
     * 博客正文
     */
    private String content;

    /**
     * 博客类别id
     */
    private Category category;

    /**
     * 博客标签列表
     */
    private List<Tag> tags;

    /**
     * 博客创建时间
     */
    private Date createTime;

    /**
     * 博客更新时间
     */
    private Date updateTime;

    /**
     * 观看次数
     */
    private Integer views;

    /**
     * 文章字数
     */
    private Integer words;

    /**
     * 公开或私密
     */
    private Boolean published;

    /**
     * 是否置顶
     */
    private Boolean top;

    /**
     * 是否开启评论
     */
    private Boolean commentEnable;

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 博客标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 博客标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 博客展示图
     */
    public String getHomePicture() {
        return homePicture;
    }

    /**
     * 博客展示图
     */
    public void setHomePicture(String homePicture) {
        this.homePicture = homePicture;
    }

    /**
     * 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 博客正文
     */
    public String getContent() {
        return content;
    }

    /**
     * 博客正文
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 博客类别
     */
    public Category getCategory() {
        return category;
    }

    /**
     * 博客类别
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * 博客标签列表
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * 博客标签列表
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    /**
     * 博客创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 博客创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 博客更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 博客更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 观看次数
     */
    public Integer getViews() {
        return views;
    }

    /**
     * 观看次数
     */
    public void setViews(Integer views) {
        this.views = views;
    }

    /**
     * 文章字数
     */
    public Integer getWords() {
        return words;
    }

    /**
     * 文章字数
     */
    public void setWords(Integer words) {
        this.words = words;
    }

    /**
     * 公开或私密
     */
    public Boolean getPublished() {
        return published;
    }

    /**
     * 公开或私密
     */
    public void setPublished(Boolean published) {
        this.published = published;
    }

    /**
     * 是否置顶
     */
    public Boolean getTop() {
        return top;
    }

    /**
     * 是否置顶
     */
    public void setTop(Boolean top) {
        this.top = top;
    }

    /**
     * 是否开启评论
     */
    public Boolean getCommentEnable() {
        return commentEnable;
    }

    /**
     * 是否开启评论
     */
    public void setCommentEnable(Boolean commentEnable) {
        this.commentEnable = commentEnable;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", homePicture='" + homePicture + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", category=" + category +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", views=" + views +
                ", words=" + words +
                ", published=" + published +
                ", top=" + top +
                ", commentEnable=" + commentEnable +
                '}';
    }
}