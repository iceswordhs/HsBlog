package com.hs.constants.exception;

/**
 * 自定义错误请求异常
 * @author Hs
 * @Date 2021/11/30 16:31
 */
public class BadRequestException extends RuntimeException{
    BadRequestException(){

    }
    public BadRequestException(String message){
        super(message);
    }
    BadRequestException(String message,Throwable cause){
        super(message, cause);
    }
}
