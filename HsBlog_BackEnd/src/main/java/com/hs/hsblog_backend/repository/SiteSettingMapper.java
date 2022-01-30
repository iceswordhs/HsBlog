package com.hs.hsblog_backend.repository;

import com.hs.hsblog_backend.entity.SiteSetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.hsblog_backend.entity.SiteSetting
 */
@Mapper
public interface SiteSettingMapper {
    Integer saveSiteSetting(SiteSetting setting);

    Integer updateSiteSetting(SiteSetting setting);

    Integer deleteById(Integer id);

    List<SiteSetting> getSiteSettingByZhName(String zhName);

    List<SiteSetting> getSiteSettingByEnName(String enName);

    List<SiteSetting> getSiteSettingByType(Integer type);

    List<Integer> getAllType();
}




