package com.hs.controller.admin;

import com.github.pagehelper.PageInfo;
import com.hs.annotation.OperationAnnotation;
import com.hs.entity.Friend;
import com.hs.entity.FriendItem;
import com.hs.entity.NullObject;
import com.hs.service.FriendService;
import com.hs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @OperationAnnotation("保存友链页面设置")
    @PostMapping("friendItem")
    public Result<NullObject> saveFriendItem(@RequestBody FriendItem friendItem){
        friendService.saveFriendItem(friendItem);
        return Result.success();
    }

    @OperationAnnotation("保存友链链接")
    @PutMapping("friendItem")
    public Result<NullObject> updateFriendItem(@RequestBody FriendItem friendItem){
        friendService.updateFriendItem(friendItem);
        return Result.success();
    }

    @OperationAnnotation("根据id删除友链")
    @DeleteMapping("friendItem")
    public Result<NullObject> DeleteFriendItem(@RequestParam Long id){
        friendService.deleteFriendItemById(id);
        return Result.success();
    }

    @OperationAnnotation("更新友链公开状态")
    @PostMapping("friendItem/published")
    public Result<NullObject> updatePublishedById(@RequestParam Long id){
        friendService.updatePublishedById(id);
        return Result.success();
    }

    @GetMapping("friendInfo")
    public Result<Friend> getFriendInfo(){
        Friend friendInfo = friendService.getFriendInfo();
        return Result.success(friendInfo);
    }

    @OperationAnnotation("更新友链页面评论开启状态")
    @PutMapping("friendInfo/commentEnable")
    public Result<NullObject> updateCommentEnabled(@RequestParam Boolean commentEnable){
        System.out.println(commentEnable);
        friendService.updateCommentEnable(commentEnable);
        return Result.success();
    }

    @OperationAnnotation("更新友链页面描述")
    @PutMapping("friendInfo/content")
    public Result<NullObject> updateContent(@RequestParam String content){
        System.out.println(content);
        friendService.updateContent(content);
        return Result.success();
    }
}
