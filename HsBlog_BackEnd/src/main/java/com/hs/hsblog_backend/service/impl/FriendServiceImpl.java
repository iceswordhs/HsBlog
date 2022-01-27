package com.hs.hsblog_backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.entity.Friend;
import com.hs.hsblog_backend.entity.FriendItem;
import com.hs.hsblog_backend.repository.FriendItemMapper;
import com.hs.hsblog_backend.repository.FriendMapper;
import com.hs.hsblog_backend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Hs
 * @Date 2022/1/24 15:40
 */
@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    FriendMapper friendMapper;
    @Autowired
    FriendItemMapper friendItemMapper;

    private String orderBy="create_time desc";

    @Override
    public PageInfo<FriendItem> getFriendList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,orderBy);
        return new PageInfo<>(friendItemMapper.getFriendItemList());
    }

    @Override
    public Friend getFriendInfo() {
        return friendMapper.getFriendInfo();
    }

    @Override
    public void saveFriendItem(FriendItem friendItem) {
        friendItem.setCreateTime(new Date());
        friendItemMapper.saveFriendItem(friendItem);
    }

    @Override
    public void updateFriendItem(FriendItem friendItem) {
        friendItemMapper.updateFriendItem(friendItem);
    }

    @Override
    public void deleteFriendItemById(Integer id) {
        friendItemMapper.deleteFriendItemById(id);
    }

    @Override
    public void updatePublishedById(Integer id) {
        friendItemMapper.deleteFriendItemById(id);
    }

    @Override
    public void updateContent(String content) {
        friendMapper.updateContent(content);
    }

    @Override
    public void updateCommentEnable(Boolean commentEnable) {
        friendMapper.updateCommentEnable(commentEnable);
    }


    @Override
    public Friend getFriendView() {
        List<FriendItem> friendItemList = friendItemMapper.getFriendItemList();
        Friend friend = friendMapper.getFriendInfo();
        friend.setFriendItemList(friendItemList);
        return friend;
    }
}
