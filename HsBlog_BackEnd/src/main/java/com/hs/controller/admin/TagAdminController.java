package com.hs.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.annotation.OperationAnnotation;
import com.hs.entity.NullObject;
import com.hs.entity.Tag;
import com.hs.service.TagService;
import com.hs.util.Result;
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
        List<Tag> tags = tagService.getTagLists();
        PageInfo<Tag> tagPageInfo = new PageInfo<>(tags);
        return Result.success(tagPageInfo);
    }

    @OperationAnnotation("添加标签")
    @PostMapping("/addTag")
    public Result<NullObject> addTag(@RequestBody Tag tag){
        tagService.addTag(tag);
        return Result.success();
    }

    @OperationAnnotation("更新标签")
    @PostMapping("/updateTag")
    public Result<NullObject> updateTag(@RequestBody Tag tag){
        tagService.updateTagById(tag);
        return Result.success();
    }

    @OperationAnnotation("根据Id删除标签")
    @DeleteMapping("/deleteTagById")
    public Result<NullObject> deleteTagById(@RequestParam Long id){
        tagService.deleteTagById(id);
        return Result.success();
    }
}
