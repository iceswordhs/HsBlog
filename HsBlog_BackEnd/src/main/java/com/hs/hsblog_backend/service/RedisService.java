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

    boolean hasKey(String key);

    // 给key设置过期时间
    void expire(String key, long time);
}
