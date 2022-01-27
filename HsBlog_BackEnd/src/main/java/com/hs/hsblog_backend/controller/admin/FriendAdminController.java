package com.hs.hsblog_backend.controller.admin;

import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.entity.Friend;
import com.hs.hsblog_backend.entity.FriendItem;
import com.hs.hsblog_backend.entity.NullObject;
import com.hs.hsblog_backend.service.FriendService;
import com.hs.hsblog_backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Hs
 * @Date 2022/1/24 15:32
 */
@RestController
@RequestMapping("/admin")
public class FriendAdminController {
    @Autowired
    FriendService friendService;

    @GetMapping("friendItems")
    public Result<PageInfo<FriendItem>> getFriends(@RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "5") Integer pageSize){
        PageInfo<FriendItem> friendList = friendService.getFriendList(pageNum, pageSize);
        return Result.success(friendList);
    }

    @PostMapping("friendItem")
    public Result<NullObject> saveFriendItem(@RequestBody FriendItem friendItem){
        friendService.saveFriendItem(friendItem);
        return Result.success();
    }

    @PutMapping("friendItem")
    public Result<NullObject> updateFriendItem(@RequestBody FriendItem friendItem){
        friendService.updateFriendItem(friendItem);
        return Result.success();
    }

    @DeleteMapping("friendItem")
    public Result<NullObject> DeleteFriendItem(@RequestParam Integer id){
        friendService.deleteFriendItemById(id);
        return Result.success();
    }

    @PostMapping("friendItem/published")
    public Result<NullObject> updatePublishedById(@RequestParam Integer id){
        friendService.deleteFriendItemById(id);
        return Result.success();
    }

    @GetMapping("friendInfo")
    public Result<Friend> getFriendInfo(){
        Friend friendInfo = friendService.getFriendInfo();
        return Result.success(friendInfo);
    }

    @PutMapping("friendInfo/commentEnable")
    public Result<NullObject> updateCommentEnabled(@RequestParam Boolean commentEnable){
        System.out.println(commentEnable);
        friendService.updateCommentEnable(commentEnable);
        return Result.success();
    }

    @PutMapping("friendInfo/content")
    public Result<NullObject> updateContent(@RequestParam String content){
        System.out.println(content);
        friendService.updateContent(content);
        return Result.success();
    }
}
