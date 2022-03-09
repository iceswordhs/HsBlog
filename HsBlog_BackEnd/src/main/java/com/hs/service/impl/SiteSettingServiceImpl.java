package com.hs.service.impl;

import com.github.pagehelper.PageHelper;
import com.hs.constants.RedisKey;
import com.hs.entity.SiteSetting;
import com.hs.model.bean.Badge;
import com.hs.repository.SiteSettingMapper;
import com.hs.service.RedisService;
import com.hs.service.SiteSettingService;
import com.hs.util.JacksonUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hs
 * @Date 2022/1/25 23:26
 */
@Service
public class SiteSettingServiceImpl implements SiteSettingService {
    @Autowired
    SiteSettingMapper siteSettingMapper;
    @Autowired
    RedisService redisService;

    private static final Pattern PATTERN = Pattern.compile("\"(.*?)\"");

    @Override
    public Map<String, Object> getSite() {
        String siteInfoMap = RedisKey.SITE_INFO_MAP;
        Map<String, Object> mapByKey = redisService.getMapByKey(siteInfoMap);
        if (mapByKey!=null){
            return mapByKey;
        }

        HashMap<String, Object> map = new HashMap<>();
        //List<Integer> types = siteSettingMapper.getAllType();
        //for (Integer type : types) {
        //    List<SiteSetting> siteSettingByType = siteSettingMapper.getSiteSettingByType(type);
        //    HashMap<String, Object> typeMap = new HashMap<>();
        //    if (type==3){
        //        ArrayList<SiteSetting> customizes = new ArrayList<>();
        //        for (SiteSetting setting:siteSettingByType){
        //            if (!"customize".equals(setting.getNameEn())){
        //                typeMap.put(setting.getNameEn(),setting);
        //            }else{
        //                customizes.add(setting);
        //            }
        //        }
        //        typeMap.put("customize",customizes);
        //    }else {
        //        for (SiteSetting setting : siteSettingByType) {
        //            typeMap.put(setting.getNameEn(),setting);
        //        }
        //    }
        //    map.put("type"+type,typeMap);
        //}
        map.put("introduction",getIntroduction());
        map.put("badges",getBadges());
        map.put("siteSetting",getSiteSetting());
        redisService.saveMapToValue(siteInfoMap,map);
        return map;
    }
    @Override
    public Map<String, Object> getIntroduction() {
        List<SiteSetting> siteSettingByType = siteSettingMapper.getSiteSettingByType(3);
        HashMap<String, Object> map = new HashMap<>();
        List<String> customizes = new ArrayList<>();
        for (SiteSetting setting : siteSettingByType) {
            if ("customize".equals(setting.getNameEn())){
                customizes.add(setting.getValue());
                continue;
            }
            if ("rollText".equals(setting.getNameEn())){
                ArrayList<String> rollTexts = new ArrayList<>();
                Matcher m = PATTERN.matcher(setting.getValue());
                while (m.find()) {
                    rollTexts.add(m.group(1));
                }
                map.put("rollText",rollTexts);
                continue;
            }
            map.put(setting.getNameEn(),setting.getValue());
        }
        map.put("customizes",customizes);
        return map;
    }

    public List<Badge> getBadges(){
        List<SiteSetting> sitting = siteSettingMapper.getSiteSettingByType(2);
        ArrayList<Badge> badges = new ArrayList<>();
        for (SiteSetting badge : sitting) {
            Badge b = JacksonUtils.readValue(badge.getValue(), Badge.class);
            badges.add(b);
        }
        return badges;
    }

    public Map<String,Object> getSiteSetting(){
        List<SiteSetting> siteSettingByType = siteSettingMapper.getSiteSettingByType(1);
        HashMap<String, Object> map = new HashMap<>();
        for (SiteSetting setting : siteSettingByType) {
            map.put(setting.getNameEn(),setting.getValue());
        }
        return map;
    }


    /**
     * Admin
     */
    @Override
    public Map<String, Object> getAllTypeSiteSetting() {
        List<Integer> types = siteSettingMapper.getAllType();
        HashMap<String, Object> map = new HashMap<>();
        for (Integer type : types) {
            // type大于3的不在这个页面
            if (type>3) continue;
            List<SiteSetting> siteSettingByType = siteSettingMapper.getSiteSettingByType(type);
            map.put("type"+type,siteSettingByType);
        }
        return map;
    }

    @Override
    public void updateSiteSetting(List<LinkedHashMap> siteSettings, List<Long> deleteIds) {
        for (Long id : deleteIds) {//删除
            deleteOneSiteSettingById(id);
        }
        for (LinkedHashMap s : siteSettings) {
            SiteSetting siteSetting = JacksonUtils.convertValue(s, SiteSetting.class);
            if (siteSetting.getId() != null) {//修改
                updateOneSiteSetting(siteSetting);
            } else {//添加
                saveOneSiteSetting(siteSetting);
            }
        }

        // 删除Redis缓存
        deleteRedisCache();
    }

    @Transactional
    public void saveOneSiteSetting(SiteSetting siteSetting) {
        if (siteSettingMapper.saveSiteSetting(siteSetting) != 1) {
            throw new PersistenceException("配置添加失败");
        }
    }

    @Transactional
    public void updateOneSiteSetting(SiteSetting siteSetting) {
        if (siteSettingMapper.updateSiteSetting(siteSetting) != 1) {
            throw new PersistenceException("配置修改失败");
        }
    }

    @Transactional
    public void deleteOneSiteSettingById(Long id) {
        if (siteSettingMapper.deleteById(id) != 1) {
            throw new PersistenceException("配置删除失败");
        }
    }

    private void deleteRedisCache(){
        redisService.deleteCacheByKey(RedisKey.SITE_INFO_MAP);
    }

    /**
     * 获取不同的格言,暂时废弃
     * @return
     */
    @Override
    public List<SiteSetting> getSomeSentences(){
        return siteSettingMapper.getSomeSentences();
    }

    @Override
    public List<SiteSetting> getSentences(){
        List<SiteSetting> sentences = siteSettingMapper.getSiteSettingByType(4);
        return sentences;
    }

    @Override
    public void updateSentences(List<LinkedHashMap> sentences, List<Long> deleteIds){
        for (Long id : deleteIds) {//删除
            deleteOneSiteSettingById(id);
        }
        for (LinkedHashMap s : sentences) {
            SiteSetting sentence = JacksonUtils.convertValue(s, SiteSetting.class);
            if (sentence.getId() != null) {//修改
                updateOneSiteSetting(sentence);
            } else {//添加
                saveOneSiteSetting(sentence);
            }
        }
        redisService.deleteCacheByKey(RedisKey.SENTENCES_SET);
    }

    @Override
    public SiteSetting getOneSentences() {
        String redisKey=RedisKey.SENTENCES_SET;
        if (redisService.hasKey(redisKey)){
            return (SiteSetting) redisService.getRandomValueFromSet(redisKey);
        }
        // 如果不存在 就把sentences全部保存到redis
        List<SiteSetting> sentences = siteSettingMapper.getSiteSettingByType(4);
        for (SiteSetting sentence : sentences) {
            redisService.saveValueToSet(redisKey,sentence);
        }
        return (SiteSetting) redisService.getRandomValueFromSet(redisKey);
    }
}
