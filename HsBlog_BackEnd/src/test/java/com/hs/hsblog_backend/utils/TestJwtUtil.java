package com.hs.hsblog_backend.utils;

import com.hs.hsblog_backend.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

/**
 * @author Hs
 * @Date 2022/2/19 17:11
 */
@SpringBootTest
public class TestJwtUtil {
    @Test
    void testJwt(){
        Claims tokenBody = JwtUtil.getTokenBody("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIcyIsImF1dGhvcml0aWVzIjoiUk9MRV92aXNpdG9yLFJPTEVfYWRtaW4sIiwiZXhwIjoxNjQ1NzUyODMxfQ.38Jmu6qjpuNYsngizSyVVUV7SuSzvDlhR9JFArnqTEE");
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) tokenBody.get("authorities"));
        authorities.forEach(a-> System.out.println(a.getAuthority()));
    }
}
