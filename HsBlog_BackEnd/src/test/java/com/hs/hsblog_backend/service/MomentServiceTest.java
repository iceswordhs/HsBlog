package com.hs.hsblog_backend.service;

import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.entity.Moment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Hs
 * @Date 2022/1/18 0:26
 */
@SpringBootTest
@Transactional
public class MomentServiceTest {
    @Autowired
    MomentService momentService;

    @Test
    void test(){
        PageInfo<Moment> momentByPageNum = momentService.findMomentByPageNum(1, true);
        System.out.println(momentByPageNum);
    }
}
