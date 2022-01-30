package com.hs.hsblog_backend.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Hs
 * @Date 2022/1/29 0:02
 */
@SpringBootTest
public class RedisServiceTest {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void test(){
        redisTemplate.opsForHash().put("k1","field1","hv1");
        String value=(String) redisTemplate.opsForHash().get("k1","field1");
        System.out.println(value);
    }
}
