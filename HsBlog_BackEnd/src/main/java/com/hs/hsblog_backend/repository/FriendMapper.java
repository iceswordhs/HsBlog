package com.hs.hsblog_backend.repository;

import com.hs.hsblog_backend.entity.Friend;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity com.hs.hsblog_backend.entity.Friend
 */
@Mapper
public interface FriendMapper {
    Friend getFriendInfo();

    void updateContent(String content);

    void updateCommentEnable(Boolean commentEnable);
}




