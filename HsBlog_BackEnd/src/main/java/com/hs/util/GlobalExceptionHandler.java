package com.hs.util;

import com.hs.constants.CodeType;
import com.hs.constants.exception.BadRequestException;
import com.hs.constants.exception.PasswordIncorrectException;
import com.hs.constants.exception.ServiceException;
import com.hs.constants.exception.UserNotExistException;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Hs
 * @Date 2021/11/29 21:41
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //非法请求
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @ResponseBody
    public Result<Exception> handlerBadRequestException(Exception ex,HttpServletRequest request){
        return Result.fail(CodeType.BAD_REQUEST, ex);
    }

    // jwt过期(无法捕捉！)
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @ResponseBody
    public Result<Exception> handlerExpiredJwtException(Exception ex,HttpServletRequest request){
        return Result.fail(CodeType.VOUCHER_HAS_EXPIRED, ex);
    }

    //密码错误
    @ExceptionHandler(PasswordIncorrectException.class)
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @ResponseBody
    public Result<Exception> handlerPasswordIncorrectException(Exception ex,HttpServletRequest request){
        return Result.fail(CodeType.PASSWORD_INCORRECT, ex);
    }

    //用户不存在
    @ExceptionHandler({UserNotExistException.class, UsernameNotFoundException.class})
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @ResponseBody
    public Result<Exception> handlerUserNotExistException(Exception ex){
        return Result.fail(CodeType.USER_NOT_EXIST, ex);
    }

    // http参数无法解析
    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @ResponseBody
    public Result<String> handlerHttpMessageConversionException(Exception ex){
        return Result.fail(CodeType.HTTP_MESSAGE_CONVERT_ERROR, ex.getMessage());
    }

    // Service层异常
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @ResponseBody
    public Result<String> handlerServiceException(Exception ex){
        return Result.fail(CodeType.SERVICE_ERROR, ex.getMessage());
    }

    // 存储层异常
    @ExceptionHandler(PersistenceException.class)
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @ResponseBody
    public Result<String> handlerPersistenceException(Exception ex){
        return Result.fail(CodeType.SERVICE_ERROR, ex.getMessage());
    }

    //自定义异常处理结果类，为前后端分离返回统一样式结果做准备。
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @ResponseBody
    public Result<Exception> handlerExceptionReturnResult(Exception ex,HttpServletRequest request){
        return Result.<Exception>generate(1000, "报错URI为" +request.getRequestURI()
                + "异常类型为"+ex.getClass().toString()
                + "发生时间为"+new Date(), ex);
    }
}
