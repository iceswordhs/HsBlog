package com.hs.hsblog_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mvc配置
 * @author Hs
 * @Date 2021/11/30 9:35
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 配置拦截器支持跨域
     * @author huangshuai
     * @Date 2021/11/30 9:39
     * @param registry 登记器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET","POST","PUT","DELETE","HEAD","OPTIONS")
                .maxAge(3600);
    }
}
