package com.hs.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Hs
 * @Date 2022/6/17 13:07
 */
@SpringBootTest
public class SignCalendarMapperTest {
    @Autowired
    SignCalendarMapper signCalendarMapper;

    @Disabled
    @Test
    void testFindDateSignStatus(){
        System.out.println(signCalendarMapper.findDateSignStatus("2022-05-29"));
        signCalendarMapper.morningSign("2022-05-29");
        System.out.println(signCalendarMapper.findDateSignStatus("2022-05-29"));
        signCalendarMapper.nightSign("2022-05-29");
        System.out.println(signCalendarMapper.findDateSignStatus("2022-05-29"));
    }
}
