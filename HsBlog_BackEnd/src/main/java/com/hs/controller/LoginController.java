package com.hs.controller;

import com.hs.constants.CodeType;
import com.hs.entity.User;
import com.hs.model.dto.LoginInfo;
import com.hs.service.UserService;
import com.hs.util.JwtUtil;
import com.hs.util.Result;
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
        user.setTrueName(null);
        user.setBirthday(null);
        user.setPhone(null);
        String jwt = JwtUtil.generateToken("admin:" + user.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("token", jwt);
        return Result.success(map);
    }
}
