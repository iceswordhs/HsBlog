package com.hs;

import com.hs.entity.User;
import com.hs.repository.UserMapper;
import com.hs.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
class HsBlogBackEndApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads() {
        //User user = userMapper.findByUsername("hs");
        //System.out.println(user);
        UserDetails hs = userService.loadUserByUsername("Hs");
        System.out.println(hs);
        User admin = (User)hs;
        System.out.println(admin);
    }

}
