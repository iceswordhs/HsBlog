package com.hs.model.vo;

import com.hs.entity.Category;
import com.hs.entity.Tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 前端博客列表对应实体
 * @author Hs
 * @Date 2022/1/21 0:12
 */
public class BlogListItem {
    private Long id;
    private String title;// 文章标题
    private String description;// 描述
    private String homePicture;// 首图
    private Date createTime;// 创建时间
    private Integer views;// 浏览次数
    private Integer words;// 文章字数
    private Boolean top;// 是否置顶
    private Boolean published;// 是否公开

    private Category category;// 文章分类
    private List<Tag> tags = new ArrayList<>();//文章标签

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHomePicture() {
        return homePicture;
    }

    public void setHomePicture(String homePicture) {
        this.homePicture = homePicture;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getWords() {
        return words;
    }

    public void setWords(Integer words) {
        this.words = words;
    }

    public Boolean getTop() {
        return top;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "BlogListItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", homePicture='" + homePicture + '\'' +
                ", createTime=" + createTime +
                ", views=" + views +
                ", words=" + words +
                ", top=" + top +
                ", published=" + published +
                ", category=" + category +
                ", tags=" + tags +
                '}';
    }
}
