package com.hs.hsblog_backend.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Hs
 * @Date 2022/2/10 19:51
 */
public class PageComment {
    private Long id;
    private String nickname;//昵称
    private String content;//评论内容
    private String avatar;//头像(图片路径)
    private Date createTime;//评论时间
    private String website;//个人网站
    private Boolean adminComment;//博主回复
    private String parentCommentId;//父评论id
    private String parentCommentNickname;//父评论昵称

    private List<PageComment> replyComments = new ArrayList<>();//回复该评论的评论

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Boolean getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(Boolean adminComment) {
        this.adminComment = adminComment;
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getParentCommentNickname() {
        return parentCommentNickname;
    }

    public void setParentCommentNickname(String parentCommentNickname) {
        this.parentCommentNickname = parentCommentNickname;
    }

    public List<PageComment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<PageComment> replyComments) {
        this.replyComments = replyComments;
    }
}
