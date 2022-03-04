package com.hs.config;

import com.hs.entity.NullObject;
import com.hs.constants.CodeType;
import com.hs.util.JacksonUtils;
import com.hs.util.JwtUtil;
import com.hs.util.Result;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 每个需要验证的请求都验证token是否正确
 * Jwt过滤器 对于/admin/**请求判断是否有Jwt，及Jwt是否过期
 * 如果有Jwt，获取对应权限和用户名，将他们组装成SpringSecurity中的UsernamePasswordAuthenticationToken
 * 将UsernamePasswordAuthenticationToken放入securityContextHolder
 * @author Hs
 * @Date 2021/11/29 21:23
 */
public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 如果不是后台页面请求，直接跳过,进行后续处理
        if (!request.getRequestURI().startsWith("/admin")){
            filterChain.doFilter(request,response);
            return;
        }
        String jwt = request.getHeader("Authorization");

        // 当jwt不为空时，才会尝试获取权限
        if (JwtUtil.checkTokenIsNotNull(jwt)) {
            try{
            Claims tokenBody = JwtUtil.getTokenBody(jwt);
            String username = tokenBody.getSubject();
            // 从token中获取对应权限
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) tokenBody.get("authorities"));
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,null, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("token已过期，重新登录！");
                response.setContentType("application/json;charset=utf-8");
                Result<NullObject> result = Result.fail(CodeType.VOUCHER_HAS_EXPIRED);
                PrintWriter out = response.getWriter();
                out.write(JacksonUtils.toJSONString(result));
                out.flush();
                out.close();
                return;
            }
        }
        //else {
        //    // 没有登录过的人应该是游客，只有visitor，如果他们想要访问admin，就应该跳转到登录页
        //    List<GrantedAuthority> authorities= AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_visitor");
        //    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("visitor",null, authorities);
        //    SecurityContextHolder.getContext().setAuthentication(token);
        //}
        // 继续执行过滤器
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
