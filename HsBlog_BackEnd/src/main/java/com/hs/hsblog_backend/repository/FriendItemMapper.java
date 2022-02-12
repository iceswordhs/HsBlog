package com.hs.hsblog_backend.repository;

import com.hs.hsblog_backend.entity.FriendItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.hsblog_backend.entity.FriendItem
 */
@Mapper
public interface FriendItemMapper {
    List<FriendItem> getFriendItemList();

    List<FriendItem> getPublishedFriendItemList();

    void saveFriendItem(FriendItem friendItem);

    void updateFriendItem(FriendItem friendItem);

    void deleteFriendItemById(Long id);

    int updateViewsByNickName(String nickName);
}




