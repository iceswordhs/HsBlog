package com.hs.service;

import com.hs.constants.RedisKey;
import com.hs.entity.About;
import com.hs.repository.AboutMapper;
import org.junit.jupiter.api.Disabled;
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
    @Autowired
    RedisService redisService;
    @Autowired
    AboutMapper aboutMapper;

    //@Test
    void test(){
        redisTemplate.opsForHash().put("k1","field1","hv1");
        String value=(String) redisTemplate.opsForHash().get("k1","field1");
        redisTemplate.delete("k1");
        System.out.println(value);
    }

    //@Test
    void testAboutRedis(){
        About about = aboutMapper.findAbout();
        redisService.saveObjectToString("aObject",about);
        About aObject = redisService.getObjectByKeyFromString("aObject",About.class);
        System.out.println(aObject);
    }

    @Test
    @Disabled
    void testDeleteCacheByKey(){
        redisService.deleteCacheByKey(RedisKey.SITE_INFO_MAP);
    }
}
