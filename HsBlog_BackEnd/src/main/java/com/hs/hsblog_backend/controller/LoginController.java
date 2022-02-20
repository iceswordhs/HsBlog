package com.hs.hsblog_backend.controller;

import com.hs.hsblog_backend.constants.CodeType;
import com.hs.hsblog_backend.entity.NullObject;
import com.hs.hsblog_backend.entity.User;
import com.hs.hsblog_backend.model.dto.LoginInfo;
import com.hs.hsblog_backend.service.UserService;
import com.hs.hsblog_backend.util.JwtUtil;
import com.hs.hsblog_backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hs
 * @Date 2022/2/19 11:22
 */
@RestController
public class LoginController {
    @Autowired
    UserService userService;

    /**
     * 登录成功后，签发博主身份Token
     *
     * @param loginInfo
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginInfo loginInfo) {
        User user = userService.checkUsernameAndPassword(loginInfo.getUsername(), loginInfo.getPassword());
        if (!user.getRole().contains("ROLE_admin")) {
            return Result.success(CodeType.NO_HAVE_AUTHORITY);
        }
        user.setPassword(null);
        String jwt = JwtUtil.generateToken("admin:" + user.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("token", jwt);
        return Result.success(map);
    }
}
