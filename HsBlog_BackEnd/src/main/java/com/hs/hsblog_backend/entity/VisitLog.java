package com.hs.hsblog_backend.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 访问日志
 * @TableName visit_log
 */
public class VisitLog implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 访客标识码
     */
    private String uuid;

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
     * 访问行为
     */
    private String behavior;

    /**
     * 访问内容
     */
    private String content;

    /**
     * 备注
     */
    private String remark;

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
     * 访问时间
     */
    private Date createTime;

    /**
     * user-agent用户代理
     */
    private String userAgent;

    private static final long serialVersionUID = 1L;


    public VisitLog(){

    }

    public VisitLog(String uuid, String uri, String method, String behavior, String content, String remark, String ip, Integer times, String userAgent) {
        this.uuid = uuid;
        this.uri = uri;
        this.method = method;
        this.behavior = behavior;
        this.content = content;
        this.remark = remark;
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
     * 访客标识码
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 访客标识码
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
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
     * 访问行为
     */
    public String getBehavior() {
        return behavior;
    }

    /**
     * 访问行为
     */
    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    /**
     * 访问内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 访问内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
     * 访问时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 访问时间
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