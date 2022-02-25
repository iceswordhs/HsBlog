package com.hs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 访客实体
 * @TableName visitor
 */
public class Visitor implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 访客标识码
     */
    private String uuid;

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
     * 首次访问时间
     */
    private Date createTime;

    /**
     * 最后访问时间
     */
    private Date lastTime;

    /**
     * 访问页数统计
     */
    private Integer pv;

    /**
     * user-agent用户代理
     */
    private String userAgent;

    private static final long serialVersionUID = 1L;

    public Visitor(){

    }

    public Visitor(String uuid, String ip, String userAgent) {
        this.uuid = uuid;
        this.ip = ip;
        Date date = new Date();
        this.createTime = date;
        this.lastTime = date;
        this.pv = 0;
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
     * 首次访问时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 首次访问时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后访问时间
     */
    public Date getLastTime() {
        return lastTime;
    }

    /**
     * 最后访问时间
     */
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 访问页数统计
     */
    public Integer getPv() {
        return pv;
    }

    /**
     * 访问页数统计
     */
    public void setPv(Integer pv) {
        this.pv = pv;
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