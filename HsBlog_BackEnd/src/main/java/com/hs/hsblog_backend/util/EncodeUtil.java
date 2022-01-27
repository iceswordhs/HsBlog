package com.hs.hsblog_backend.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 加密工具类
 * @author Hs
 * @Date 2021/11/30 20:07
 */
@Component
public class EncodeUtil {
    private static final BCryptPasswordEncoder bc=new BCryptPasswordEncoder();

    public static boolean matchPassword(CharSequence rawPassword,String encodePassword){
        return bc.matches(rawPassword, encodePassword);
    }
}
