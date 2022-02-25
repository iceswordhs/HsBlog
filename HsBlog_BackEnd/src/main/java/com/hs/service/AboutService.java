package com.hs.service;

import com.hs.entity.About;

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

    /**
     * 管理页面获取About
     * @return
     */
    public About getAboutInfoManage();

    void updateAbout(About about);

    void insertAbout(About about);

    Boolean getAboutCommentEnabled();
}
