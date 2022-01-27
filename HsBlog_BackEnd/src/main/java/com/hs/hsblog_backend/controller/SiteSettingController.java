package com.hs.hsblog_backend.controller;

import com.hs.hsblog_backend.service.SiteSettingService;
import com.hs.hsblog_backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Hs
 * @Date 2022/1/25 23:52
 */
@RestController
public class SiteSettingController {
    @Autowired
    SiteSettingService siteSettingService;

    @GetMapping("site")
    public Result<Map<String, Object>> getSite(){
        Map<String, Object> site = siteSettingService.getSite();
        return Result.success(site);
    }
}
