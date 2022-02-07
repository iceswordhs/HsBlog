package com.hs.hsblog_backend.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于记录访客访问记录的注解
 * @author Hs
 * @Date 2022/2/6 20:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisitLogger {
    /**
     * 访问行为
     */
    String behavior() default "";

    /**
     * 访问内容
     */
    String content() default "";
}
