package com.hs.hsblog_backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

/**
 * Jwt工具类
 * 注意：由于spring的构造顺序是先执行构造方法再执行注入方法，所以如果用static修饰无法注入，只能使用一个方法来注入
 * @author Hs
 * @Date 2021/11/29 20:55
 */
@Component
public class JwtUtil {

    private static long expireTime;

    private static String secretKey;

    @Value("${token.expireTime}")
    public void setExpireTime(long expireTime){
        JwtUtil.expireTime=expireTime;
    }
    @Value("${token.secretKey}")
    public void setSecretKey(String secretKey) {
        JwtUtil.secretKey = secretKey;
    }

    // 判断token是否存在
    public static boolean checkTokenIsNotNull(String token){
        return token!=null&&!"".equals(token);
    }

    /**
     * 生成Token
     * @author huangshuai
     * @Date 2021/11/29 21:12
     * @param subject 用户
     * @return java.lang.String
     */
    public static String generateToken(String subject){
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis()+expireTime))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }

    /**
     * 生成带角色权限的token
     * @author huangshuai
     * @Date 2021/11/29 21:18
     * @param subject 用户
     * @param authorities 权限集合
     * @return java.lang.String
     */
    public static String generateToken(String subject, Collection<? extends GrantedAuthority> authorities){
        StringBuilder sb=new StringBuilder();
        for (GrantedAuthority grantedAuthority : authorities) {
            sb.append(grantedAuthority).append(",");
        }
        return Jwts.builder()
                .setSubject(subject)
                .claim("authorities",sb)
                .setExpiration(new Date(System.currentTimeMillis()+expireTime))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }


    /**
     * 获取tokenBody同时校验token是否有效（无效则会抛出异常）
     * @author huangshuai
     * @Date 2021/11/29 21:22
     * @param token token
     * @return io.jsonwebtoken.Claims
     */
    public static Claims getTokenBody(String token){
        Claims claims = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token.replace("Bearer", ""))
                .getBody();
        return claims;
    }

    /**
     * 检验token签名是否正确
     * @author huangshuai
     * @Date 2021/12/1 9:46
     * @param token jwt
     * @return boolean
     */
    private static boolean checkTokenIsRight(String token){
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token.replace("Bearer", ""));
            return true;
        }catch (JwtException e){
            return false;
        }
    }
}
