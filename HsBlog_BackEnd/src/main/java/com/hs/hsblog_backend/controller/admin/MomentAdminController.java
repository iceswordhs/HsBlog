package com.hs.hsblog_backend.controller.admin;

import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.annotation.OperationAnnotation;
import com.hs.hsblog_backend.entity.Moment;
import com.hs.hsblog_backend.entity.NullObject;
import com.hs.hsblog_backend.service.MomentService;
import com.hs.hsblog_backend.util.JwtUtil;
import com.hs.hsblog_backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hs
 * @Date 2022/1/17 22:50
 */
@RequestMapping("/admin")
@RestController
public class MomentAdminController {
    @Autowired
    MomentService momentService;

    @OperationAnnotation("添加动态")
    @PostMapping("/createMoment")
    public Result<NullObject> createMoment(@RequestBody Moment moment){
        momentService.createMoment(moment);
        return Result.success();
    }

    @OperationAnnotation("更改动态")
    @PostMapping("/updateMoment")
    public Result<NullObject> updateMoment(@RequestBody Moment moment){
        momentService.updateMoment(moment);
        return Result.success();
    }

    @GetMapping("/getMomentById")
    public Result<Moment> getMomentById(Long id){
        return Result.success(momentService.findMomentById(id));
    }



    @RequestMapping("/manageMoments")
    public Result<PageInfo<Moment>> manageMoments(@RequestParam(defaultValue = "1") Integer pageNum,
                                              @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<Moment> moments = momentService.findMomentByPageNumAndPageSize(pageNum,pageSize);
        return Result.success(moments);
    }

    @OperationAnnotation("根据Id删除动态")
    @PostMapping("/deleteMoment")
    public Result<Long> deleteMoment(@RequestParam Long id){
        momentService.deleteMomentById(id);
        return Result.success(id);
    }

    @OperationAnnotation("更新动态公开状态")
    @PostMapping("/updateMomentPublished")
    public Result<NullObject> updateMomentPublished(@RequestParam Long id,@RequestParam Boolean publish){
        momentService.updatePublishedById(id, publish);
        return Result.success();
    }
}
