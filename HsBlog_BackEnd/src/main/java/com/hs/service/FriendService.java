package com.hs.service;

import com.github.pagehelper.PageInfo;
import com.hs.entity.Friend;
import com.hs.entity.FriendItem;

/**
 * @author Hs
 * @Date 2022/1/24 15:39
 */
public interface FriendService {
    PageInfo<FriendItem> getFriendList(Integer pageNum, Integer pageSize);

    PageInfo<FriendItem> getPublishedFriendList(Integer pageNum, Integer pageSize);

    Friend getFriendInfo();

    void saveFriendItem(FriendItem friendItem);

    void updateFriendItem(FriendItem friendItem);

    void updatePublishedById(Long id);

    void deleteFriendItemById(Long id);

    void updateContent(String content);

    void updateCommentEnable(Boolean commentEnable);

    Friend getFriendView();


    void updateViewsByNickname(String nickName);
}
