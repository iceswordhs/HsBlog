package com.hs.hsblog_backend;

import com.hs.hsblog_backend.entity.User;
import com.hs.hsblog_backend.repository.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HsBlogBackEndApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = userMapper.findByUsername("hs");
        System.out.println(user);
    }

}
