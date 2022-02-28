package com.hs.repository;

import com.hs.entity.SiteSetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.entity.SiteSetting
 */
@Mapper
public interface SiteSettingMapper {
    Integer saveSiteSetting(SiteSetting setting);

    Integer updateSiteSetting(SiteSetting setting);

    Integer deleteById(Long id);

    List<SiteSetting> getSiteSettingByZhName(String zhName);

    List<SiteSetting> getSiteSettingByEnName(String enName);

    List<SiteSetting> getSiteSettingByType(Integer type);

    List<Integer> getAllType();

    List<SiteSetting> getSomeSentences();

    SiteSetting getOneSentence();
}




