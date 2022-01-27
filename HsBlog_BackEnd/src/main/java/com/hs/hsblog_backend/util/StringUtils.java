package com.hs.hsblog_backend.util;

/**
 * @author Hs
 * @Date 2021/12/16 11:21
 */
public class StringUtils {
    public static boolean isEmpty(String... str){
        for (String s:str){
            if (s==null||"".equals(s.trim()))
                return true;
        }
        return false;
    }
}
