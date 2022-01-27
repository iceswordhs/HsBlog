package com.hs.hsblog_backend.service.impl;

import com.hs.hsblog_backend.entity.SiteSetting;
import com.hs.hsblog_backend.repository.SiteSettingMapper;
import com.hs.hsblog_backend.service.SiteSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hs
 * @Date 2022/1/25 23:26
 */
@Service
public class SiteSettingServiceImpl implements SiteSettingService {
    @Autowired
    SiteSettingMapper siteSettingMapper;

    @Override
    public Map<String, Object> getSite() {
        HashMap<String, Object> map = new HashMap<>();
        List<SiteSetting> introduction = siteSettingMapper.getSiteSettingByType(1);
        map.put("introduction",introduction);
        return map;
    }

    @Override
    public Map<String, String> getIntroduction() {
        return null;
    }
}
