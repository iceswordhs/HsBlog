package com.hs.hsblog_backend.service;

import com.hs.hsblog_backend.entity.Friend;
import com.hs.hsblog_backend.repository.FriendMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Hs
 * @Date 2022/1/24 19:54
 */
@SpringBootTest
@Transactional
@Rollback
public class FriendServiceTest {
    @Autowired
    FriendService friendService;
    @Autowired
    FriendMapper friendMapper;

    @Test
    void testFriend(){
        Friend friendInfo = friendService.getFriendInfo();
        System.out.println(friendInfo);
    }

    //@Test
    void testFriendMapper(){
        friendService.updateCommentEnable(false);
    }
}
