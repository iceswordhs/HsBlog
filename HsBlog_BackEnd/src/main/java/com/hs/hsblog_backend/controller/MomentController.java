package com.hs.hsblog_backend.controller;

import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.entity.Moment;
import com.hs.hsblog_backend.entity.NullObject;
import com.hs.hsblog_backend.service.MomentService;
import com.hs.hsblog_backend.service.impl.UserServiceImpl;
import com.hs.hsblog_backend.util.JwtUtil;
import com.hs.hsblog_backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Hs
 * @Date 2022/1/17 22:49
 */
@RestController
public class MomentController {
    @Autowired
    MomentService momentService;
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/moment")
    public Result<PageInfo<Moment>> getMoment(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestHeader(value = "Authorization", defaultValue = "") String jwt){
        boolean adminIdentity = false;
        if (JwtUtil.checkTokenIsNotNull(jwt)) {
            try {
                String subject = JwtUtil.getTokenBody(jwt).getSubject();
                System.out.println(subject);
                if (subject.startsWith("admin:")) {//博主身份Token
                    String username = subject.replace("admin:", "");
                    User admin = (User) userService.loadUserByUsername(username);
                    if (admin != null) {
                        adminIdentity = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        PageInfo<Moment> moments = momentService.findMomentByPageNum(pageNum, adminIdentity);
        return Result.success(moments);
    }

    @RequestMapping("/updateMomentLikes")
    public Result<NullObject> updateMomentLikes(@RequestParam Long id){
        momentService.updateMomentLikes(id);
        return Result.success();
    }
}
