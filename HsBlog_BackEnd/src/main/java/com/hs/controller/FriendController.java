package com.hs.controller;

import com.hs.entity.Friend;
import com.hs.entity.NullObject;
import com.hs.annotation.VisitLogger;
import com.hs.service.FriendService;
import com.hs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hs
 * @Date 2022/1/24 20:38
 */
@RestController
public class FriendController {
    @Autowired
    FriendService friendService;

    @VisitLogger(behavior = "访问页面", content = "友链")
    @GetMapping("/friends")
    public Result<Friend> getFriend(){
        Friend friendView = friendService.getFriendView();
        return Result.success(friendView);
    }

    @VisitLogger(behavior = "点击友链")
    @PostMapping("/friend")
    public Result<NullObject> addViews(@RequestParam String nickname) {
        friendService.updateViewsByNickname(nickname);
        return Result.success();
    }
}
