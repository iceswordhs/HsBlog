package com.hs.hsblog_backend.service;

import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.entity.Friend;
import com.hs.hsblog_backend.entity.FriendItem;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Hs
 * @Date 2022/1/24 15:39
 */
public interface FriendService {
    PageInfo<FriendItem> getFriendList(Integer pageNum, Integer pageSize);

    Friend getFriendInfo();

    void saveFriendItem(FriendItem friendItem);

    void updateFriendItem(FriendItem friendItem);

    void updatePublishedById(Long id);

    void deleteFriendItemById(Long id);

    void updateContent(String content);

    void updateCommentEnable(Boolean commentEnable);

    Friend getFriendView();
}
