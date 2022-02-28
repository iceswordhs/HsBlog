package com.hs.service;

import com.hs.entity.SiteSetting;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hs
 * @Date 2022/1/25 22:48
 */
public interface SiteSettingService {
    Map<String,Object> getSite();

    Map<String,Object> getIntroduction();

    List<SiteSetting> getSomeSentences();


    /**
     * Admin
     */
    Map<String,Object> getAllTypeSiteSetting();

    void updateSiteSetting(List<LinkedHashMap> siteSettings, List<Long> deleteIds);

    /**
     * Admin
     */
    List<SiteSetting> getSentences();

    void updateSentences(List<LinkedHashMap> sentences, List<Long> deleteIds);

    SiteSetting getOneSentences();
}
