package com.hs.hsblog_backend.controller.admin;

import com.hs.hsblog_backend.constants.RedisKey;
import com.hs.hsblog_backend.entity.CityVisitor;
import com.hs.hsblog_backend.service.DashboardService;
import com.hs.hsblog_backend.service.RedisService;
import com.hs.hsblog_backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hs
 * @Date 2022/2/20 20:52
 */
@RestController
@RequestMapping("admin")
public class DashboardAdminController {
    @Autowired
    DashboardService dashboardService;
    @Autowired
    RedisService redisService;

    @GetMapping("/dashboard")
    public Result dashboard() {
        int todayPV = dashboardService.countVisitLogByToday();
        int todayUV = redisService.countBySet(RedisKey.IDENTIFICATION_SET);
        int blogCount = dashboardService.getBlogCount();
        int commentCount = dashboardService.getCommentCount();
        Map<String, List> categoryBlogCountMap = dashboardService.getCategoryBlogCountMap();
        Map<String, List> tagBlogCountMap = dashboardService.getTagBlogCountMap();
        Map<String, List> visitRecordMap = dashboardService.getVisitRecordMap();
        List<CityVisitor> cityVisitorList = dashboardService.getCityVisitorList();

        Map<String, Object> map = new HashMap<>();
        map.put("pv", todayPV);
        map.put("uv", todayUV);
        map.put("blogCount", blogCount);
        map.put("commentCount", commentCount);
        map.put("category", categoryBlogCountMap);
        map.put("tag", tagBlogCountMap);
        map.put("visitRecord", visitRecordMap);
        map.put("cityVisitor", cityVisitorList);
        return Result.success(map,"获取成功");
    }
}
