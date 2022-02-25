package com.hs.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * @author Hs
 * @Date 2022/2/8 17:41
 */
@SpringBootTest
public class TestIpdb {
    //@Test
    void testReadFile() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("ipdb/ip2region.db");
        System.out.println(classPathResource);
        InputStream inputStream = classPathResource.getInputStream();
        //InputStream resourceAsStream = this.getClass().getResourceAsStream("ipdb/ip2region.db");
        //System.out.println(resourceAsStream);
    }
}
