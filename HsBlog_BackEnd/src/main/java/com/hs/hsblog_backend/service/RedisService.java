package com.hs.hsblog_backend.service;

import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.model.vo.BlogListItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Hs
 * @Date 2022/1/29 0:16
 */
public interface RedisService {
    // 从Redis中获取每页的缓存
    PageInfo<BlogListItem> getBlogListItemPageResultByHash(String key, Integer pageNum);

    // 将一个键值对保存到hash中
    void saveKVToHash(String key, Object field, Object value);

    // 将Map保存到Redis中
    void saveMapToHash(String key, Map map);

    // 根据key获取Map
    Map getMapByHash(String key);

    // 根据hash及hashKey获取对应的值
    Object getValueByHashKey(String hash, Object hashKey);

    // 对于hash中对应的key增加increment
    void incrementByHashKey(String hash, Object key, int increment);

    //void deleteByHashKey(String hash, Object key);
    //
    //<T> List<T> getListByValue(String key);
    //
    //<T> void saveListToValue(String key, List<T> list);
    //
    //<T> Map<String, T> getMapByValue(String key);
    //
    //<T> void saveMapToValue(String key, Map<String, T> map);
    //
    //<T> T getObjectByValue(String key, Class t);
    //
    //void incrementByKey(String key, int increment);
    //
    //void saveObjectToValue(String key, Object object);
    //
    //void saveValueToSet(String key, Object value);
    //
    //int countBySet(String key);
    //
    //void deleteValueBySet(String key, Object value);
    //
    //boolean hasValueInSet(String key, Object value);
    //
    //void deleteCacheByKey(String key);

    // 判断是否包含这个key
    boolean hasKey(String key);

    // 给key设置过期时间
    void expire(String key, long time);
}
