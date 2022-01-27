package com.hs.hsblog_backend.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 友链页面
 * @TableName friend
 */
public class Friend implements Serializable {
    /**
     * 页面正文
     */
    private String content;

    /**
     * 是否开启评论
     */
    private Boolean commentEnable;

    private List<FriendItem> friendItemList;

    private static final long serialVersionUID = 1L;

    /**
     * 页面正文
     */
    public String getContent() {
        return content;
    }

    /**
     * 页面正文
     */
    public void setContent(String content) {
        this.content = content;
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

    public List<FriendItem> getFriendItemList() {
        return friendItemList;
    }

    public void setFriendItemList(List<FriendItem> friendItemList) {
        this.friendItemList = friendItemList;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "content='" + content + '\'' +
                ", commentEnable=" + commentEnable +
                ", friendItemList=" + friendItemList +
                '}';
    }
}