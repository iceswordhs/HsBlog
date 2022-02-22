package com.hs.hsblog_backend.interceptor;

import com.hs.hsblog_backend.annotation.AccessLimit;
import com.hs.hsblog_backend.entity.NullObject;
import com.hs.hsblog_backend.service.RedisService;
import com.hs.hsblog_backend.util.IpAddressUtils;
import com.hs.hsblog_backend.util.JacksonUtils;
import com.hs.hsblog_backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Hs
 * @Date 2022/2/10 16:25
 */
@Component
public class AccessLimitInterceptor implements HandlerInterceptor {
    @Autowired
    RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            HandlerMethod method = (HandlerMethod) handler;
            AccessLimit methodAnnotation = method.getMethodAnnotation(AccessLimit.class);
            if (methodAnnotation==null) return true;

            int seconds=methodAnnotation.seconds();
            int maxCounts= methodAnnotation.maxCount();
            String ip= IpAddressUtils.getIpAddress(request);
            String requestMethod=request.getMethod();
            String requestURI=request.getRequestURI();
            String redisKey=ip+":"+requestURI+":"+requestMethod;
            Integer counts = redisService.getObjectByKeyFromString(redisKey, Integer.class);
            if (counts==null){
                //在规定周期内第一次访问，存入redis
                redisService.saveObjectToString(redisKey, 1);
                redisService.expire(redisKey, seconds);
            }else {
                if (counts<maxCounts){
                    redisService.incrementByKey(redisKey,1);
                    return true;
                }else {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = response.getWriter();
                    Result<NullObject> result = Result.generate(403, methodAnnotation.message(), new NullObject());
                    writer.write(JacksonUtils.toJSONString(result));
                    writer.flush();
                    writer.close();
                    return false;
                }
            }
        }
        return true;
    }
}
