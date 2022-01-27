package com.hs.hsblog_backend.config;

import com.hs.hsblog_backend.constants.CodeType;
import com.hs.hsblog_backend.entity.NullObject;
import com.hs.hsblog_backend.util.JacksonUtils;
import com.hs.hsblog_backend.util.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Hs
 * @Date 2021/12/1 13:54
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result<NullObject> result = Result.fail(CodeType.NO_HAVE_AUTHORITY);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JacksonUtils.toJSONString(result));
        writer.flush();
        writer.close();
    }
}
