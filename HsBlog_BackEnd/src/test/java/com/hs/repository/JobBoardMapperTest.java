package com.hs.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Hs
 * @Date 2022/6/19 0:57
 */
@SpringBootTest
public class JobBoardMapperTest {
    @Autowired
    JobBoardMapper mapper;

    @Test
    void testGetBoardJob(){
        System.out.println(mapper.getBoardJob());
    }
}
