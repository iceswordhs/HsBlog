package com.hs.hsblog_backend.task;

import com.hs.hsblog_backend.constants.RedisKey;
import com.hs.hsblog_backend.service.BlogService;
import com.hs.hsblog_backend.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * @author Hs
 * @Date 2022/2/6 14:47
 */
@Component
public class RedisSyncScheduleTask {
    @Autowired
    RedisService redisService;
    @Autowired
    BlogService blogService;

    /**
     * 从Redis同步博客文章浏览量到数据库
     */
    public void syncBlogViewsToDatabase() {
        String redisKey = RedisKey.BLOG_VIEWS_MAP;
        Map blogViewsMap = redisService.getMapByHash(redisKey);
        Set<Integer> keys = blogViewsMap.keySet();
        for (Integer key : keys) {
            Integer views = (Integer) blogViewsMap.get(key);
            blogService.updateViews(key.longValue(), views);
        }
    }
}
