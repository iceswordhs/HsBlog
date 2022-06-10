package com.hs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.constants.RedisKey;
import com.hs.entity.Friend;
import com.hs.entity.FriendItem;
import com.hs.repository.FriendItemMapper;
import com.hs.repository.FriendMapper;
import com.hs.service.FriendService;
import com.hs.service.RedisService;
import com.hs.util.commarkUtil.MarkDownToHTMLUtil;
import org.apache.ibatis.exceptions.PersistenceException;
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
    @Autowired
    RedisService redisService;

    private String orderBy="create_time desc";

    @Override
    public PageInfo<FriendItem> getFriendList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,orderBy);
        return new PageInfo<>(friendItemMapper.getFriendItemList());
    }

    @Override
    public PageInfo<FriendItem> getPublishedFriendList(Integer pageNum, Integer pageSize) {
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

        // 删除Redis缓存
        deleteRedisCache();
    }

    @Override
    public void updateFriendItem(FriendItem friendItem) {
        friendItemMapper.updateFriendItem(friendItem);
        // 删除Redis缓存
        deleteRedisCache();
    }

    @Override
    public void deleteFriendItemById(Long id) {
        friendItemMapper.deleteFriendItemById(id);

        // 删除Redis缓存
        deleteRedisCache();
    }

    @Override
    public void updatePublishedById(Long id) {
        friendItemMapper.deleteFriendItemById(id);

        // 删除Redis缓存
        deleteRedisCache();
    }

    @Override
    public void updateContent(String content) {
        friendMapper.updateContent(content);

        // 删除Redis缓存
        deleteRedisCache();
    }

    @Override
    public void updateCommentEnable(Boolean commentEnable) {
        friendMapper.updateCommentEnable(commentEnable);

        // 删除Redis缓存
        deleteRedisCache();
    }


    @Override
    public Friend getFriendView() {
        String friendInfoMap = RedisKey.FRIEND_INFO_MAP;
        Friend friendResult = redisService.getObjectByKeyFromString(friendInfoMap, Friend.class);
        if (friendResult!=null) return friendResult;
        List<FriendItem> friendItemList = friendItemMapper.getPublishedFriendItemList();
        Friend friend = friendMapper.getFriendInfo();
        friend.setFriendItemList(friendItemList);
        friend.setContent(MarkDownToHTMLUtil.markdownToHtml(friend.getContent()));
        redisService.saveObjectToString(friendInfoMap,friend);
        return friend;
    }

    @Override
    public void updateViewsByNickname(String nickName) {
        if(friendItemMapper.updateViewsByNickName(nickName)!=1){
            throw new PersistenceException("更新友链点击数失败");
        }
    }

    private void deleteRedisCache(){
        redisService.deleteCacheByKey(RedisKey.FRIEND_INFO_MAP);
    }
}
