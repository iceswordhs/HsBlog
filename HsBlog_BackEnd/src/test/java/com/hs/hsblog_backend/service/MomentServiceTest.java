package com.hs.hsblog_backend.service;

import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.entity.Moment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Hs
 * @Date 2022/1/18 0:26
 */
@SpringBootTest
public class MomentServiceTest {
    @Autowired
    MomentService momentService;

    @Test
    void test(){
        PageInfo<Moment> momentByPageNum = momentService.findMomentByPageNum(1, true);
        System.out.println(momentByPageNum);
    }
}
