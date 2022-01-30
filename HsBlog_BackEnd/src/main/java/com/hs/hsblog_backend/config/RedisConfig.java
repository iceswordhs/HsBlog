package com.hs.hsblog_backend.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author Hs
 * @Date 2022/1/29 0:07
 */
@Configuration
public class RedisConfig{
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();

        //配置Jackson2JsonRedisSerializer
        Jackson2JsonRedisSerializer<Object> jacksonRedisSerializer=new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om=new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonRedisSerializer.setObjectMapper(om);

        redisTemplate.setDefaultSerializer(jacksonRedisSerializer);
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
}
