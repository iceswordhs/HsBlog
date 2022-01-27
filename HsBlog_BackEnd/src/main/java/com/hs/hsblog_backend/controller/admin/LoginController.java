package com.hs.hsblog_backend.controller.admin;

import com.hs.hsblog_backend.entity.User;
import com.hs.hsblog_backend.service.UserService;
import com.hs.hsblog_backend.util.JwtUtil;
import com.hs.hsblog_backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录controller
 * 已经使用LoginFilter实现了，所以可以不要，暂时保留
 * @author Hs
 * @Date 2021/11/30 19:44
 */
@RestController
public class LoginController {
    @Autowired
    UserService userService;

    /**
     * 如果用户名和密码正确，将密码抹除，生成token，返回前端
     * @author huangshuai
     * @Date 2021/12/1 9:48
     * @param loginUser 前端传入的用户
     * @return com.hs.hsblog_backend.util.Result<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @PostMapping("/admin/login")
    public Result<Map<String,Object>> login(@RequestBody User loginUser){
        User user = userService.checkUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
        user.setPassword("");
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());
        String token = JwtUtil.generateToken(user.getUsername(),authorities);
        Map<String, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("user",user);
        return Result.success(map);
    }
}
