package com.hs.service;

import com.hs.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Hs
 * @Date 2022/2/23 14:03
 */
@SpringBootTest
public class ExceptionLogTest {
    //@Test
    void testEToString(){
        RuntimeException e = new RuntimeException("xx");
        System.out.println(StringUtils.getStackTrace(e));
    }
}
