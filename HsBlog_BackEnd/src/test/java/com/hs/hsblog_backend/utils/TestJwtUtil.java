package com.hs.hsblog_backend.utils;

import com.hs.hsblog_backend.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Hs
 * @Date 2022/2/19 17:11
 */
@SpringBootTest
public class TestJwtUtil {
    @Test
    void testJwt(){
        String token = JwtUtil.generateToken("admin:Hs");
        //JwtUtil.getTokenBody("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIcyIsImF1dGhvcml0aWVzIjoiUk9MRV9hZG1pbixST0xFX3Zpc2l0b3IsIiwiZXhwIjoxNjQ0NzU3ODg4fQ.1_TrMU0gWoPtV4lsnhOwei4g9aQzMVgHco8SJcy7CYc");
        JwtUtil.getTokenBody("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjpIcyIsImV4cCI6MTY0NTM0ODYwOH0.X9Utt9su9nD1OvgRyBZFag9Gt_4xMuA0nB8dWIgl99A").getSubject();
        System.out.println(token);
    }
}
