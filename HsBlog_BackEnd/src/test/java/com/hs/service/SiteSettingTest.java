package com.hs.service;

import com.hs.entity.SiteSetting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Hs
 * @Date 2022/2/28 15:33
 */
@SpringBootTest
public class SiteSettingTest {
    @Autowired
    SiteSettingService siteSettingService;

    @Test
    void testSentences(){
        //for (SiteSetting sentence : siteSettingService.getSomeSentences()) {
        //    System.out.println(sentence);
        //}
        for(int i=0;i<5;i++){
            System.out.println(siteSettingService.getOneSentences());
        }
    }
}
