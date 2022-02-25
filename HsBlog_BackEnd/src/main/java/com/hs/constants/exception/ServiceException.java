package com.hs.constants.exception;

/**
 * @author Hs
 * @Date 2021/12/16 11:26
 */
public class ServiceException extends RuntimeException{
    private String message;


    public static ServiceException create(String message){
        ServiceException serviceException = new ServiceException();
        serviceException.setMessage(message);
        return serviceException;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
