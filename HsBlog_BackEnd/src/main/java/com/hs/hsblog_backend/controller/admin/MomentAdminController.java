package com.hs.hsblog_backend.controller.admin;

import com.github.pagehelper.PageInfo;
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

    @PostMapping("/createMoment")
    public Result<NullObject> createMoment(@RequestBody Moment moment){
        momentService.createMoment(moment);
        return Result.success();
    }

    @PostMapping("/updateMoment")
    public Result<NullObject> updateMoment(@RequestBody Moment moment){
        momentService.updateMoment(moment);
        return Result.success();
    }

    @GetMapping("/getMomentById")
    public Result<Moment> getMomentById(Integer id){
        return Result.success(momentService.findMomentById(id));
    }



    @RequestMapping("/manageMoments")
    public Result<PageInfo<Moment>> manageMoments(@RequestParam(defaultValue = "1") Integer pageNum,
                                              @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<Moment> moments = momentService.findMomentByPageNumAndPageSize(pageNum,pageSize);
        return Result.success(moments);
    }

    @PostMapping("/deleteMoment")
    public Result<Integer> deleteMoment(@RequestParam Integer id){
        momentService.deleteMomentById(id);
        return Result.success(id);
    }

    @PostMapping("/updateMomentPublished")
    public Result<NullObject> updateMomentPublished(@RequestParam Integer id,@RequestParam Boolean publish){
        momentService.updatePublishedById(id, publish);
        return Result.success();
    }
}
