package com.hs.service;

import com.github.pagehelper.PageInfo;
import com.hs.model.vo.BlogListItem;

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

    // 保存对象到keyValue中
    void saveObjectToString(String key,Object object);
    // 根据key使value增长
    void incrementByKey(String key, int increment);
    // 从String中获取对象
    <T> T getObjectByKeyFromString(String key,Class t);

    // 将Map保存到Redis中
    void saveMapToHash(String key, Map map);

    // 根据key获取Map
    Map getMapByHash(String key);

    // 根据hash及hashKey获取对应的值
    Object getValueByHashKey(String hash, Object hashKey);
    // 对于hash中对应的key增加increment
    void incrementByHashKey(String hash, Object key, int increment);
    //删除hash中对应的hashkey的值
    void deleteByHashKey(String key, Object hashKey);


     <T> List<T> getListByValue(String key);

     <T> void saveListToValue(String key, List<T> list);


    // 根据key获取对应值转为Map
    <T> Map<String, T> getMapByKey(String key);
    // 将map存入Redis
    <T> void saveMapToValue(String key, Map<String, T> map);



    void saveValueToSet(String key, Object value);

    int countBySet(String key);

    void deleteValueBySet(String key, Object value);

    boolean hasValueInSet(String key, Object value);

    // 根据key清除key对应的值(缓存)
    void deleteCacheByKey(String key);

    // 判断是否包含这个key
    boolean hasKey(String key);

    // 给key设置过期时间
    void expire(String key, long time);
}
