package com.hs.controller;

import com.hs.entity.User;
import com.hs.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hs
 * @Date 2021/11/29 16:01
 */
@RestController
public class UserController {

    /**
     * chagnshi
     * @author huangshuai
     * @Date 2021/11/29 16:09
     * @return java.lang.String
     */
    @RequestMapping("/test")
    public Result<User> getUser(){
        System.out.println("/test被请求");
        User user=new User("hsssss","pwd");
        return Result.success(user);
    }
}
