package com.hs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 访问控制注解  用于控制请求频率
 * 注释这个注解的方法会在在拦截器中被拦截，然后通过Redis判断是否达到访问间隔
 * @author Hs
 * @Date 2022/2/10 16:19
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    /**
     * 多少秒是一个周期
     * @return
     */
    int seconds() default 10;

    /**
     * 一个周期最多访问多少次
     * @return
     */
    int maxCount() default 2;

    /**
     * 当触发限制时的消息提示
     */
    String message() default "访问过于频繁，请稍后再试";
}
