package com.hs.hsblog_backend.controller.admin;

import com.hs.hsblog_backend.annotation.OperationAnnotation;
import com.hs.hsblog_backend.entity.NullObject;
import com.hs.hsblog_backend.service.SiteSettingService;
import com.hs.hsblog_backend.util.Result;
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
}
