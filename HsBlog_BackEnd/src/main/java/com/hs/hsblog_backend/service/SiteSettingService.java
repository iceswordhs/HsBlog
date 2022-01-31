package com.hs.hsblog_backend.service;

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


    /**
     * Admin
     */
    Map<String,Object> getAllTypeSiteSetting();

    void updateSiteSetting(List<LinkedHashMap> siteSettings, List<Long> deleteIds);
}
