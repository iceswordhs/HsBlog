package com.hs.hsblog_backend.config;

import com.hs.hsblog_backend.constants.CodeType;
import com.hs.hsblog_backend.constants.exception.BadRequestException;
import com.hs.hsblog_backend.entity.NullObject;
import com.hs.hsblog_backend.entity.User;
import com.hs.hsblog_backend.util.JacksonUtils;
import com.hs.hsblog_backend.util.JwtUtil;
import com.hs.hsblog_backend.util.Result;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录过滤器 用于登录
 * 已有LoginController，不需要此类
 * @author Hs
 * @Date 2021/11/30 16:22
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    protected LoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        try {
            if (!"POST".equals(request.getMethod())){
                throw new BadRequestException("请求方法错误");
            }
            User user = JacksonUtils.readValue(request.getInputStream(), User.class);
            assert user != null;
            System.out.println(user);
            // 交给SpringSecurity去认证，他会调用所有的认证方式，只要有一种认证方式成功即可。
            // 其中有一个认证方式为AbstractUserDetailsAuthenticationProvider，
            // 他会调用authenticate这个方法，这个方法由它的子类DaoAuthenticationProvider实现，
            // 这个子类会调用UserDetailsService的loadUserByUsername方法取得用户，然后进行对比
            return getAuthenticationManager()
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        } catch (BadRequestException e){
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            Result<NullObject> result = Result.fail(CodeType.BAD_REQUEST);
            writer.write(JacksonUtils.toJSONString(result));
            writer.flush();
            writer.close();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 生成token
        String token = JwtUtil.generateToken(authResult.getName(), authResult.getAuthorities());
        Map<String,Object> map=new HashMap<>(4);
        // Principal SpringSecurity中的主体，谁使用系统谁就是主体
        System.out.println(authResult.getPrincipal());
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        User user = new User(principal.getUsername(), principal.getPassword());
        user.setAvatarImgUrl("https://typoraimage.smarths.cn/faivon.jpeg");
        System.out.println("自定义User"+user);
        map.put("user",user);
        map.put("token",token);
        Result<Map<String, Object>> result = Result.success(map);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JacksonUtils.toJSONString(result));
        writer.flush();
        writer.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Result<NullObject> result = Result.fail(CodeType.LOGIN_FAIL);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JacksonUtils.toJSONString(result));
        writer.flush();
        writer.close();
    }
}
