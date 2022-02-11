package com.hs.hsblog_backend.service;

import com.hs.hsblog_backend.entity.About;

import java.util.Map;

/**
 * @author Hs
 * @Date 2022/1/14 21:29
 */
public interface AboutService {
    /**
     * 获取About页面各项属性信息
     * @author huangshuai
     * @Date 2022/1/14 21:33
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    About getAboutInfo();

    void updateAbout(About about);

    void insertAbout(About about);

    Boolean getAboutCommentEnabled();
}
