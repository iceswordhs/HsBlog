package com.hs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 友链链接
 * @TableName friend_item
 */
public class FriendItem implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 描述
     */
    private String description;

    /**
     * 站点url
     */
    private String websiteUrl;

    /**
     * 站点头像
     */
    private String avatar;

    /**
     * 是否公开
     */
    private Boolean published;

    /**
     * 浏览次数
     */
    private Integer views;

    /**
     * 
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
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
     * 站点url
     */
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    /**
     * 站点url
     */
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    /**
     * 站点头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 站点头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 是否公开
     */
    public Boolean getPublished() {
        return published;
    }

    /**
     * 是否公开
     */
    public void setPublished(Boolean published) {
        this.published = published;
    }

    /**
     * 浏览次数
     */
    public Integer getViews() {
        return views;
    }

    /**
     * 浏览次数
     */
    public void setViews(Integer views) {
        this.views = views;
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
}