package com.hs.hsblog_backend.service.impl;

import com.hs.hsblog_backend.constants.RedisKey;
import com.hs.hsblog_backend.entity.About;
import com.hs.hsblog_backend.repository.AboutMapper;
import com.hs.hsblog_backend.service.AboutService;
import com.hs.hsblog_backend.service.RedisService;
import com.hs.hsblog_backend.util.commarkUtil.MarkDownToHTMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author Hs
 * @Date 2022/1/14 21:30
 */
@Service
public class AboutServiceImpl implements AboutService {
    @Autowired
    AboutMapper aboutMapper;
    @Autowired
    RedisService redisService;

    @Override
    public About getAboutInfo() {
        String redisKey = RedisKey.ABOUT_INFO_MAP;
        About aboutResult = redisService.getObjectByKeyFromString(redisKey,About.class);
        if (aboutResult != null) {
            return aboutResult;
        }
        About about = aboutMapper.findAbout();
        about.setContent(MarkDownToHTMLUtil.markdownToHtml(about.getContent()));
        redisService.saveObjectToString(redisKey,about);
        return about;
    }

    @Override
    public void updateAbout(About about) {
        about.setUpdateTime(new Date());
        aboutMapper.updateAbout(about);
        deleteAboutRedisCache();
    }

    @Override
    public void insertAbout(About about) {
        aboutMapper.insertAbout(about);
        deleteAboutRedisCache();
    }

    /**
     * 删除关于我页面缓存
     */
    private void deleteAboutRedisCache() {
        redisService.deleteCacheByKey(RedisKey.ABOUT_INFO_MAP);
    }

    @Override
    public Boolean getAboutCommentEnabled() {
        return aboutMapper.getAboutCommentEnabled();
    }
}
