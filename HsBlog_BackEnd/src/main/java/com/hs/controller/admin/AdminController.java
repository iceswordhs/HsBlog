package com.hs.controller.admin;

import com.hs.entity.User;
import com.hs.repository.UserMapper;
import com.hs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hs
 * @Date 2021/11/30 15:40
 */
@RestController
public class AdminController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/admin/test")
    public Result<List<User>> getAllUser(){
        System.out.println("执行成功！");
        User hs = userMapper.findByUsername("Hs");

        List<User> list=new ArrayList<>();
        list.add(hs);
        return Result.success(list);
    }
}
