package com.hs.repository;

import com.hs.entity.Friend;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity com.hs.entity.Friend
 */
@Mapper
public interface FriendMapper {
    Friend getFriendInfo();

    void updateContent(String content);

    void updateCommentEnable(Boolean commentEnable);
}




