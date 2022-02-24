package com.hs.hsblog_backend.aspect;

import com.hs.hsblog_backend.annotation.OperationAnnotation;
import com.hs.hsblog_backend.annotation.VisitLogger;
import com.hs.hsblog_backend.entity.ExceptionLog;
import com.hs.hsblog_backend.service.ExceptionLogService;
import com.hs.hsblog_backend.util.AopUtils;
import com.hs.hsblog_backend.util.IpAddressUtils;
import com.hs.hsblog_backend.util.JacksonUtils;
import com.hs.hsblog_backend.util.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Hs
 * @Date 2022/2/6 16:13
 *
 * Aop的使用，1.导入依赖 2.添加@Component @Aspect注解 3.使用@Before @After @AfterThrow等注解配置切点及执行方法
 */
@Component
@Aspect
public class ExceptionLogAspect {
    @Autowired
    ExceptionLogService exceptionLogService;

    @Pointcut(value = "execution(* com.hs.hsblog_backend.controller..*.*(..))")
    public void exceptionLogCut(){
    }

    @AfterThrowing(value = "exceptionLogCut()",throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Exception e) {
        ExceptionLog log = handleLog(joinPoint, e);
        exceptionLogService.saveExceptionLog(log);
    }

    private ExceptionLog handleLog(JoinPoint joinPoint, Exception e) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String ip = IpAddressUtils.getIpAddress(request);
        // 获取用户操作系统和浏览器类型及版本
        String userAgent = request.getHeader("User-Agent");
        //todo 使用swagger后，可以直接使用注解上的内容作为 ExceptionLog 的 description
        String description = getDescriptionFromAnnotations(joinPoint);
        String error = StringUtils.getStackTrace(e);
        ExceptionLog log = new ExceptionLog(uri, method, description, error, ip, userAgent);
        Map<String, Object> requestParams = AopUtils.getRequestParams(joinPoint);
        log.setParam(StringUtils.substring(JacksonUtils.writeValueAsString(requestParams), 0, 2000));
        return log;
    }

    private String getDescriptionFromAnnotations(JoinPoint joinPoint) {
        String description = "";
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        OperationAnnotation operationAnnotation = method.getAnnotation(OperationAnnotation.class);
        if (operationAnnotation != null) {
            description = operationAnnotation.value();
            return description;
        }
        VisitLogger visitLogger = method.getAnnotation(VisitLogger.class);
        if (visitLogger != null) {
            description = visitLogger.behavior();
            return description;
        }
        return description;
    }
}
