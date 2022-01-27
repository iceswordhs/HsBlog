package com.hs.hsblog_backend.constants.exception;

/**
 * @author Hs
 * @Date 2021/11/30 19:54
 */
public class UserNotExistException extends RuntimeException{
    public UserNotExistException(String message){
        super(message);
    }

    public UserNotExistException(String message,Throwable cause){
        super(message,cause);
    }
}
