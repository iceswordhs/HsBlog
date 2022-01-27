package com.hs.hsblog_backend.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Hs
 * @Date 2021/12/16 17:03
 */
public class DateUtil {
    private static final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String convertDateToString(Date date){
        return format.format(date);
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(convertDateToString(new Date()));

        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        System.out.println(yyyyMMdd.format(new Date()));
        System.out.println(yyyyMMdd.parse("20211221"));
    }
}
