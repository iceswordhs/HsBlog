package com.hs.aspect;

import com.hs.annotation.OperationAnnotation;
import com.hs.entity.OperationLog;
import com.hs.service.OperationLogService;
import com.hs.util.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Hs
 * @Date 2022/2/7 22:44
 */
@Component
@Aspect
public class OperationLogAspect {
    @Autowired
    OperationLogService operationLogService;

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(operationAnnotation)")
    public void logPointcut(OperationAnnotation operationAnnotation) {
    }

    /**
     * 配置环绕通知
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "logPointcut(operationAnnotation)", argNames = "joinPoint,operationAnnotation")
    public Object logAround(ProceedingJoinPoint joinPoint, OperationAnnotation operationAnnotation) throws Throwable {
        currentTime.set(System.currentTimeMillis());
        Object result = joinPoint.proceed();
        int times = (int) (System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        OperationLog operationLog = handleLog(joinPoint, operationAnnotation, times);
        operationLogService.saveOperationLog(operationLog);
        return result;
    }

    /**
     * 获取HttpServletRequest请求对象，并设置OperationLog对象属性
     *
     * @param operationAnnotation
     * @param times
     * @return
     */
    private OperationLog handleLog(ProceedingJoinPoint joinPoint, OperationAnnotation operationAnnotation, int times) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String username = JwtUtil.getTokenBody(request.getHeader("Authorization")).getSubject();
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String description = operationAnnotation.value();
        String ip = IpAddressUtils.getIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        OperationLog log = new OperationLog(username, uri, method, description, ip, times, userAgent);
        Map<String, Object> requestParams = AopUtils.getRequestParams(joinPoint);
        log.setParam(StringUtils.substring(JacksonUtils.writeValueAsString(requestParams), 0, 2000));
        return log;
    }
}
