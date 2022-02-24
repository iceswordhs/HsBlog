package com.hs.hsblog_backend.service;

import com.hs.hsblog_backend.util.StringUtils;
import org.junit.jupiter.api.Test;
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
