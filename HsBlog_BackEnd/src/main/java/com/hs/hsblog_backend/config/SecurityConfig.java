package com.hs.hsblog_backend.config;

import com.hs.hsblog_backend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SpringSecurity配置类
 * @author Hs
 * @Date 2021/11/30 10:42
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UnLoginAuthenticationEntryPoint unLoginAuthenticationEntryPoint;
    @Autowired
    MyAccessDeniedHandler accessDeniedHandler;
    @Autowired
    UserServiceImpl userService;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /**
     * 认证 如果没有相关权限，会自动返回403
     * @author huangshuai
     * @Date 2021/11/30 13:39
     * @param http
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 禁用csrf
                .csrf().disable()
                // 开启跨域支持
                .cors().and()
                // 基于token，所以session创建配置设置为无状态
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 配置请求验证
                .authorizeRequests()
                //.antMatchers("/admin/webTitleSuffix").permitAll()
                // 放行登陆请求
                .antMatchers("/admin/login").permitAll()
                .antMatchers(HttpMethod.GET,"/admin/**").hasAnyRole("admin","visitor")
                .antMatchers("/admin/**").hasRole("admin")
                // 其他路径全部放行
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(new LoginFilter("/admin/login",authenticationManager()),UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
                // 认证异常处理
                .exceptionHandling()
                // 当用户没有登录时，想访问admin，让他登录
                .authenticationEntryPoint(unLoginAuthenticationEntryPoint)
                // 访问被拒绝自定义处理
                .accessDeniedHandler(accessDeniedHandler);
    }

    /**
     * 授权 调用userService，生成有用户名、有权限的SpringSecurity的用户
     * @author huangshuai
     * @Date 2021/11/30 13:43
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }
}
