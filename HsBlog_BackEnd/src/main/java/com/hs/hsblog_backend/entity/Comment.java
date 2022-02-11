package com.hs.hsblog_backend.entity;

import com.hs.hsblog_backend.model.vo.BlogIdAndTitle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @TableName comment
 */
public class Comment implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 头像(图片路径)
     */
    private String avatar;

    /**
     * 评论时间
     */
    private Date createTime;

    /**
     * 评论者ip地址
     */
    private String ip;

    /**
     * 公开或回收站
     */
    private Boolean published;

    /**
     * 博主回复
     */
    private Boolean adminComment;

    /**
     * 0普通文章，1关于我页面，2友链页面
     */
    private Integer page;

    /**
     * 接收邮件提醒
     */
    private Boolean notice;

    /**
     * 所属的文章
     */
    private Long blogId;

    /**
     * 父评论id，-1为根评论
     */
    private Long parentCommentId;

    /**
     * 个人网站
     */
    private String website;

    /**
     * 如果评论昵称为QQ号，则将昵称和头像置为QQ昵称和QQ头像，并将此字段置为QQ号备份
     */
    private String qq;

    private BlogIdAndTitle blog;//所属的文章
    private List<Comment> replyComments = new ArrayList<>();//回复该评论的评论

    private static final long serialVersionUID = 1L;

    public BlogIdAndTitle getBlog() {
        return blog;
    }

    public void setBlog(BlogIdAndTitle blog) {
        this.blog = blog;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

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
     * 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 评论内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 头像(图片路径)
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 头像(图片路径)
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 评论时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 评论时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 评论者ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 评论者ip地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 公开或回收站
     */
    public Boolean getPublished() {
        return published;
    }

    /**
     * 公开或回收站
     */
    public void setPublished(Boolean published) {
        this.published = published;
    }

    /**
     * 博主回复
     */
    public Boolean getAdminComment() {
        return adminComment;
    }

    /**
     * 博主回复
     */
    public void setAdminComment(Boolean adminComment) {
        this.adminComment = adminComment;
    }

    /**
     * 0普通文章，1关于我页面，2友链页面
     */
    public Integer getPage() {
        return page;
    }

    /**
     * 0普通文章，1关于我页面，2友链页面
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * 接收邮件提醒
     */
    public Boolean getNotice() {
        return notice;
    }

    /**
     * 接收邮件提醒
     */
    public void setNotice(Boolean notice) {
        this.notice = notice;
    }

    /**
     * 所属的文章
     */
    public Long getBlogId() {
        return blogId;
    }

    /**
     * 所属的文章
     */
    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    /**
     * 父评论id，-1为根评论
     */
    public Long getParentCommentId() {
        return parentCommentId;
    }

    /**
     * 父评论id，-1为根评论
     */
    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    /**
     * 个人网站
     */
    public String getWebsite() {
        return website;
    }

    /**
     * 个人网站
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * 如果评论昵称为QQ号，则将昵称和头像置为QQ昵称和QQ头像，并将此字段置为QQ号备份
     */
    public String getQq() {
        return qq;
    }

    /**
     * 如果评论昵称为QQ号，则将昵称和头像置为QQ昵称和QQ头像，并将此字段置为QQ号备份
     */
    public void setQq(String qq) {
        this.qq = qq;
    }
}