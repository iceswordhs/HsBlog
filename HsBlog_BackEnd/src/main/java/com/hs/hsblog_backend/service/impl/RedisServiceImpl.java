package com.hs.hsblog_backend.service.impl;

import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.model.vo.BlogListItem;
import com.hs.hsblog_backend.service.RedisService;
import com.hs.hsblog_backend.util.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Hs
 * @Date 2022/1/29 0:16
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;


    @Override
    public PageInfo<BlogListItem> getBlogListItemPageResultByHash(String key, Integer pageNum) {
        // 在这个key下是否有pageNum对应的hashKey
        if (redisTemplate.opsForHash().hasKey(key,pageNum)){
            Object blogList = redisTemplate.opsForHash().get(key, pageNum);
            return JacksonUtils.convertValue(blogList, PageInfo.class);
        }else {
            return null;
        }
    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void saveMapToHash(String hash, Map map) {
        redisTemplate.opsForHash().putAll(hash,map);
    }

    @Override
    public void saveKVToHash(String key, Object field, Object value) {
        redisTemplate.opsForHash().put(key,field,value);
    }

    @Override
    public void saveObjectToString(String key, Object object) {
        redisTemplate.opsForValue().set(key,object);
    }

    @Override
    public <T> T getObjectByKeyFromString(String key,Class t) {
        Object o = redisTemplate.opsForValue().get(key);
        return (T)JacksonUtils.convertValue(o, t);
    }

    @Override
    public void incrementByKey(String key, int increment) {
        redisTemplate.opsForValue().increment(key,increment);
    }

    @Override
    public Object getValueByHashKey(String hash, Object hashKey) {
        return redisTemplate.opsForHash().get(hash,hashKey);
    }

    @Override
    public void incrementByHashKey(String key, Object hashKey, int increment) {
        redisTemplate.opsForHash().increment(key,hashKey,increment);
    }

    @Override
    public void deleteByHashKey(String key, Object hashKey) {
        redisTemplate.opsForHash().delete(key,hashKey);
    }

    @Override
    public <T> List<T> getListByValue(String key) {
        return (List<T>) redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> void saveListToValue(String key, List<T> list) {

    }

    @Override
    public Map getMapByHash(String hash) {
        return redisTemplate.opsForHash().entries(hash);
    }

    @Override
    public <T> Map<String, T> getMapByKey(String key) {
         return (Map<String,T>)redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> void saveMapToValue(String key, Map<String, T> map) {
        redisTemplate.opsForValue().set(key,map);
    }


    @Override
    public void saveValueToSet(String key, Object value) {
        redisTemplate.opsForSet().add(key, value);
    }

    @Override
    public int countBySet(String key) {
        return redisTemplate.opsForSet().size(key).intValue();
    }

    @Override
    public void deleteValueBySet(String key, Object value) {
        redisTemplate.opsForSet().remove(key, value);
    }

    @Override
    public boolean hasValueInSet(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }



    @Override
    public void deleteCacheByKey(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void expire(String key, long time) {
        redisTemplate.expire(key,time, TimeUnit.SECONDS);
    }
}
