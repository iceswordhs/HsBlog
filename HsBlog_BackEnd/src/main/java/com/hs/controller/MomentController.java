package com.hs.controller;

import com.github.pagehelper.PageInfo;
import com.hs.annotation.AccessLimit;
import com.hs.annotation.VisitLogger;
import com.hs.entity.Moment;
import com.hs.entity.NullObject;
import com.hs.service.MomentService;
import com.hs.service.impl.UserServiceImpl;
import com.hs.util.JwtUtil;
import com.hs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @VisitLogger(behavior = "访问页面", content = "动态")
    @RequestMapping("/moment")
    public Result<PageInfo<Moment>> getMoment(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestHeader(value = "Authorization", defaultValue = "") String jwt){
        boolean adminIdentity = false;
        if (JwtUtil.checkTokenIsNotNull(jwt)) {
            try {
                System.out.println(jwt);
                String subject = JwtUtil.getTokenBody(jwt).getSubject();
                System.out.println(subject);
                if (subject.startsWith("admin:")) {//博主身份Token
                    String username = subject.replace("admin:", "");
                    UserDetails admin = userService.loadUserByUsername(username);
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

    @AccessLimit(seconds = 86400, maxCount = 1, message = "不可以重复点赞哦")
    @VisitLogger(behavior = "点赞动态")
    @RequestMapping("/updateMomentLikes")
    public Result<NullObject> updateMomentLikes(@RequestParam Long id){
        momentService.updateMomentLikes(id);
        return Result.success();
    }
}
