package com.hs.hsblog_backend.model.vo;

import java.util.Date;

/**
 * @author Hs
 * @Date 2022/2/19 17:51
 */
public class RandomBlog {
    private Long id;
    private String title;//文章标题
    private String homePicture;//文章首图，用于随机文章展示
    private Date createTime;//创建时间

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


    @Override
    public String toString() {
        return "RandomBlog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", homePicture='" + homePicture + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
