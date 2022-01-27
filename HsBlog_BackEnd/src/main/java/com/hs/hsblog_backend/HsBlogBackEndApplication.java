package com.hs.hsblog_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class HsBlogBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(HsBlogBackEndApplication.class, args);
    }

}
