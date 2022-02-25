package com.hs.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Hs
 * @Date 2021/12/16 11:21
 */
public class StringUtils {
    /**
     * 检查字符串是否为空
     * @author huangshuai
     * @Date 2022/2/6 20:36
     * @param str 字符串参数集合
     * @return boolean
     */
    public static boolean isEmpty(String... str){
        for (String s:str){
            if (s==null||"".equals(s.trim()))
                return true;
        }
        return false;
    }

    /**
     * 检查是否包含特殊字符
     * @author huangshuai
     * @Date 2022/2/6 20:36
     * @param str 待检查字符串
     * @return boolean
     */
    public static boolean hasSpecialChar(String... str) {
        for (String s : str) {
            if (s.contains("%") || s.contains("_") || s.contains("[") || s.contains("#") || s.contains("*")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 截取字符串
     * @author huangshuai
     * @Date 2022/2/6 20:35
     * @param str 字符串
     * @param start 起始位置
     * @param end 结束位置
     * @return java.lang.String
     */
    public static String substring(String str, int start, int end) {
        if (str == null || "".equals(str)) {
            return "";
        }
        if (start < 0 || end < 0) {
            return str;
        }
        if (end > str.length()) {
            end = str.length();
        }
        if (start >= end) {
            return "";
        }
        return str.substring(start, end);
    }


    /**
     * 获取异常堆栈信息
     * @author huangshuai
     * @Date 2022/2/6 20:35
     * @param throwable 异常
     * @return java.lang.String
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
}
