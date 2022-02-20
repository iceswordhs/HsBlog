package com.hs.hsblog_backend.service;

import com.hs.hsblog_backend.entity.CityVisitor;

import java.util.List;
import java.util.Map;

/**
 * @author Hs
 * @Date 2022/2/20 20:53
 */
public interface DashboardService {
    int countVisitLogByToday();

    int getBlogCount();

    int getCommentCount();

    Map<String, List> getCategoryBlogCountMap();

    Map<String, List> getTagBlogCountMap();

    Map<String, List> getVisitRecordMap();

    List<CityVisitor> getCityVisitorList();
}
