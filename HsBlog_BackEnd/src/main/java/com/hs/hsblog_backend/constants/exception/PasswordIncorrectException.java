package com.hs.hsblog_backend.constants.exception;

/**
 * @author Hs
 * @Date 2021/11/30 19:56
 */
public class PasswordIncorrectException extends RuntimeException{
    public PasswordIncorrectException(String message){
        super(message);
    }

    public PasswordIncorrectException(String message,Throwable cause){
        super(message, cause);
    }
}
