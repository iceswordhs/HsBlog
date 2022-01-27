package com.hs.hsblog_backend.constants;

/**
 * 状态码枚举类
 * 200表示成功 400-499表示客户端错误 500-599表示服务端错误
 * @author Hs
 * @Date 2021/11/29 21:33
 */
public enum CodeType {
    /**
     * 状态码
     */
    SUCCESS(200,"success"),
    NETWORK_ANOMALY(500,"网络异常"),
    SERVICE_ERROR(400,"service层出现异常"),
    UN_LOGIN(401,"请登录"),
    VOUCHER_HAS_EXPIRED(403,"凭证已失效，请重新登录！"),
    BAD_REQUEST(410,"非法请求"),
    LOGIN_FAIL(411,"登陆失败"),
    Password_Incorrect(412,"密码错误"),
    USER_NOT_EXIST(413,"用户不存在"),
    NO_HAVE_AUTHORITY(414,"没有相应权限"),
    Http_Message_Convert_FAIL(415,"http参数转换失败")
    ;

    private final int code;

    private final String message;

    /**
     * 枚举类的构造方法  被强制为私有
     * @param code 状态码
     * @param message 状态码对应的信息
     */
    CodeType(int code,String message){
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
