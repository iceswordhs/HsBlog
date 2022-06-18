package com.hs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@EnableAsync
@SpringBootApplication
public class HsBlogBackEndApplication {

    // 解决Druid一直报连接的警告，作用等同于 在启动程序时在运行参数中增加：-Ddruid.mysql.usePingMethod=false
    // WARN  com.alibaba.druid.pool.DruidAbstractDataSource - discard long time none received connection
    static {
        System.setProperty("druid.mysql.usePingMethod","false");
    }

    public static void main(String[] args) {
        SpringApplication.run(HsBlogBackEndApplication.class, args);
    }

}
