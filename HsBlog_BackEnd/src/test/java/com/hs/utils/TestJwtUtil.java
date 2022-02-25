package com.hs.utils;

import com.hs.util.JwtUtil;
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
        Claims tokenBody = JwtUtil.getTokenBody("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIcyIsImF1dGhvcml0aWVzIjoiUk9MRV92aXNpdG9yLFJPTEVfYWRtaW4sIiwiZXhwIjoxNjQ1Nzc1Mzc0fQ.YwhDG5CiTs8sYgCBDEnfygPkVVU4w5dOXwl4lg4-xf4");
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) tokenBody.get("authorities"));
        authorities.forEach(a-> System.out.println(a.getAuthority()));
    }
}
