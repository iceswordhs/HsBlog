package com.hs.hsblog_backend.repository;

import com.hs.hsblog_backend.entity.SiteSetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.hsblog_backend.entity.SiteSetting
 */
@Mapper
public interface SiteSettingMapper {
    void insertSiteSetting(SiteSetting setting);

    void updateSiteSetting(SiteSetting setting);

    void deleteById(Integer id);

    List<SiteSetting> getSiteSettingByZhName(String zhName);

    List<SiteSetting> getSiteSettingByEnName(String enName);

    List<SiteSetting> getSiteSettingByType(Integer type);

}




