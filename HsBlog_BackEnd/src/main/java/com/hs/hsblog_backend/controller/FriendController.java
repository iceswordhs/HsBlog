package com.hs.hsblog_backend.controller;

import com.hs.hsblog_backend.annotation.VisitLogger;
import com.hs.hsblog_backend.entity.Friend;
import com.hs.hsblog_backend.service.FriendService;
import com.hs.hsblog_backend.util.Result;
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

    // TODO: 2022/2/7  
    //@VisitLogger(behavior = "点击友链")
    //@PostMapping("/friend")
    //public Result addViews(@RequestParam String nickname) {
    //    friendService.updateViewsByNickname(nickname);
    //    return Result.success();
    //}
}
