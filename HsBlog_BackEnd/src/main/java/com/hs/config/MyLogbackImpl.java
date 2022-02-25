package com.hs.config;

import org.apache.ibatis.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 重写mybatis日志输出类，让sql语句等级变为info
 * @author Hs
 * @Date 2022/2/25 15:34
 */
public class MyLogbackImpl implements Log {
    private static final Logger log = LoggerFactory.getLogger(MyLogbackImpl.class);

    public MyLogbackImpl(String clazz) {
    }

    public boolean isDebugEnabled() {
        //return true;
        return log.isInfoEnabled();
    }

    public boolean isTraceEnabled() {
        return true;
    }

    public void error(String s, Throwable e) {
        //System.err.println(s);
        log.error(s);
        e.printStackTrace(System.err);
    }

    public void error(String s) {
        //System.err.println(s);
        log.error(s);
    }

    public void debug(String s) {
        //System.out.println(s);
        log.info(s);
    }

    public void trace(String s) {
        //System.out.println(s);
        log.trace(s);
    }

    public void warn(String s) {
        //System.out.println(s);
        log.warn(s);
    }
}
