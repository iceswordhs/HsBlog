package com.hs.hsblog_backend.config;

import com.hs.hsblog_backend.constants.CodeType;
import com.hs.hsblog_backend.entity.NullObject;
import com.hs.hsblog_backend.util.JacksonUtils;
import com.hs.hsblog_backend.util.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义认证异常处理类
 * 未登录，拒绝访问 前端跳转到处理
 * @author Hs
 * @Date 2021/11/30 11:09
 */
@Component
public class UnLoginAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        Result<NullObject> result = Result.fail(CodeType.UN_LOGIN);
        writer.write(JacksonUtils.toJSONString(result));
        writer.flush();
        writer.close();
    }
}
