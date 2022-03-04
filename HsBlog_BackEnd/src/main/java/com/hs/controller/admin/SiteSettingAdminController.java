package com.hs.controller.admin;

import com.hs.entity.NullObject;
import com.hs.annotation.OperationAnnotation;
import com.hs.entity.SiteSetting;
import com.hs.service.SiteSettingService;
import com.hs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hs
 * @Date 2022/1/26 19:25
 */
@RestController
@RequestMapping("admin")
public class SiteSettingAdminController {
    @Autowired
    SiteSettingService siteSettingService;

    @GetMapping("/site")
    public Result<Map<String,Object>> getAllTypeSiteSetting(){
        Map<String, Object> siteSettings = siteSettingService.getAllTypeSiteSetting();
        return Result.success(siteSettings);
    }

    @OperationAnnotation("更新网站设置")
    @PostMapping("siteSettings")
    public Result updateAll(@RequestBody Map<String, Object> map) {
        List<LinkedHashMap> siteSettings = (List<LinkedHashMap>) map.get("settings");
        List<Long> deleteIds = (List<Long>) map.get("deleteIds");
        siteSettingService.updateSiteSetting(siteSettings, deleteIds);
        return Result.success(NullObject.INSTANCE,"更新成功");
    }

    @OperationAnnotation("更新警醒语句")
    @PostMapping("sentences")
    public Result updateSentences(@RequestBody Map<String, Object> map){
        List<LinkedHashMap> sentences = (List<LinkedHashMap>) map.get("sentences");
        List<Long> deleteIds = (List<Long>) map.get("deleteIds");
        siteSettingService.updateSiteSetting(sentences, deleteIds);
        return Result.success(NullObject.INSTANCE,"更新成功");
    }

    @GetMapping("sentences")
    public Result updateSentences(){
        List<SiteSetting> sentences = siteSettingService.getSentences();
        return Result.success(sentences);
    }
}
