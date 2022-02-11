package com.hs.hsblog_backend.util;

import com.hs.hsblog_backend.constants.CodeType;
import com.hs.hsblog_backend.entity.NullObject;

/**
 * @author Hs
 * @Date 2021/11/29 21:32
 */
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private Result(){

    }

    private Result(Integer code,String message,T data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    /**
     * 成功方法,无返回数据
     * @param <T>
     * @return
     */
    public static <T> Result<NullObject> success(){
        return new Result<>(CodeType.SUCCESS.getCode(),CodeType.SUCCESS.getMessage(),NullObject.INSTANCE);
    }

    /**
     * 成功方法，有数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.code=CodeType.SUCCESS.getCode();
        result.message=CodeType.SUCCESS.getMessage();
        result.data=data;
        return result;
    }

    /**
     * 成功方法，有数据，有message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data,String message){
        Result<T> result = new Result<>();
        result.code=CodeType.SUCCESS.getCode();
        result.message=message;
        result.data=data;
        return result;
    }

    /**
     * 失败方法，无数据
     * @param <T>
     * @return
     */
    public static <T> Result<NullObject> fail(CodeType codeType){
        return new Result<>(codeType.getCode(),codeType.getMessage(), NullObject.INSTANCE);
    }

    /**
     * 失败方法，有message
     * @param <T>
     * @return
     */
    public static <T> Result<NullObject> fail(String message){
        return new Result<>(400,message, NullObject.INSTANCE);
    }

    ///**
    // * 失败方法，有数据
    // * @author huangshuai
    // * @Date 2021/11/29 21:40
    // * @param data
    // * @return com.hs.hsblog_backend.util.Result<T>
    // */
    //public static <T> Result<T> fail(T data){
    //    Result<T> result = new Result<>();
    //    result.code=CodeType.SUCCESS.getCode();
    //    result.message=CodeType.SUCCESS.getMessage();
    //    result.data=data;
    //    return result;
    //}

    /**
     * 失败方法，有数据，还有失败对应的状态码
     * @author huangshuai
     * @Date 2021/11/29 21:36
     * @param codeType 状态码
     * @param data 数据
     * @return com.hs.hsblog_backend.util.Result<T>
     */
    public static <T> Result<T> fail(CodeType codeType, T data){
        Result<T> result = new Result<>();
        result.code=codeType.getCode();
        result.message=codeType.getMessage();
        result.data=data;
        return result;
    }

    /**
     * 当遇到特殊情况或无相应状态码时使用
     * @author huangshuai
     * @Date 2021/11/29 21:36
     * @param code 状态码
     * @param message 状态码对应信息
     * @param data 数据
     * @return com.hs.hsblog_backend.util.Result<T>
     */
    public static <T> Result<T> generate(Integer code,String message,T data){
        Result<T> result = new Result<>();
        result.code=code;
        result.data=data;
        result.message=message;
        return result;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
