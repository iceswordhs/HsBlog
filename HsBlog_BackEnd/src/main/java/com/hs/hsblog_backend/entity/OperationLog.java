package com.hs.hsblog_backend.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName operation_log
 */
public class OperationLog implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 操作者用户名
     */
    private String username;

    /**
     * 请求接口
     */
    private String uri;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 请求参数
     */
    private String param;

    /**
     * 操作描述
     */
    private String description;

    /**
     * ip
     */
    private String ip;

    /**
     * ip来源
     */
    private String ipSource;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 请求耗时（毫秒）
     */
    private Integer times;

    /**
     * 操作时间
     */
    private Date createTime;

    /**
     * user-agent用户代理
     */
    private String userAgent;

    private static final long serialVersionUID = 1L;

    public OperationLog(){

    }

    public OperationLog(String username, String uri, String method, String description, String ip, Integer times, String userAgent) {
        this.username = username;
        this.uri = uri;
        this.method = method;
        this.description = description;
        this.ip = ip;
        this.times = times;
        this.createTime = new Date();
        this.userAgent = userAgent;
    }

    /**
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 操作者用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 操作者用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 请求接口
     */
    public String getUri() {
        return uri;
    }

    /**
     * 请求接口
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * 请求方式
     */
    public String getMethod() {
        return method;
    }

    /**
     * 请求方式
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 请求参数
     */
    public String getParam() {
        return param;
    }

    /**
     * 请求参数
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * 操作描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 操作描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * ip来源
     */
    public String getIpSource() {
        return ipSource;
    }

    /**
     * ip来源
     */
    public void setIpSource(String ipSource) {
        this.ipSource = ipSource;
    }

    /**
     * 操作系统
     */
    public String getOs() {
        return os;
    }

    /**
     * 操作系统
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * 浏览器
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * 浏览器
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * 请求耗时（毫秒）
     */
    public Integer getTimes() {
        return times;
    }

    /**
     * 请求耗时（毫秒）
     */
    public void setTimes(Integer times) {
        this.times = times;
    }

    /**
     * 操作时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 操作时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * user-agent用户代理
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * user-agent用户代理
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}