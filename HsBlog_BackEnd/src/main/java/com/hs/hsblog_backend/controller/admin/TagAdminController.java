package com.hs.hsblog_backend.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.entity.NullObject;
import com.hs.hsblog_backend.entity.Tag;
import com.hs.hsblog_backend.service.TagService;
import com.hs.hsblog_backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/12/29 20:32
 */
@RestController
@RequestMapping("/admin")
public class TagAdminController {
    @Autowired
    TagService tagService;

    @GetMapping("/getAllTags")
    public Result<List<Tag>> getAllTags(){
        List<Tag> allTags = tagService.getAllTags();
        return Result.success(allTags);
    }

    @GetMapping("/getTagByPage")
    public Result<PageInfo<Tag>> getTagByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                              @RequestParam(defaultValue = "10") Integer pageSize){
        String orderBy="tag_id desc";
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<Tag> tags = tagService.getAllTags();
        PageInfo<Tag> tagPageInfo = new PageInfo<>(tags);
        return Result.success(tagPageInfo);
    }

    @PostMapping("/addTag")
    public Result<NullObject> addTag(@RequestBody Tag tag){
        tagService.addTag(tag);
        return Result.success();
    }

    @PostMapping("/updateTag")
    public Result<NullObject> updateTag(@RequestBody Tag tag){
        tagService.updateTagById(tag);
        return Result.success();
    }

    @DeleteMapping("/deleteTagById")
    public Result<NullObject> deleteTagById(@RequestParam Long id){
        tagService.deleteTagById(id);
        return Result.success();
    }
}
