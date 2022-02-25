package com.hs.entity;

import java.io.Serializable;

/**
 * 访客统计
 * @TableName visit_record
 */
public class VisitRecord implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 访问量
     */
    private Integer pv;

    /**
     * 独立用户
     */
    private Integer uv;

    /**
     * 日期"02-23"
     */
    private String date;

    private static final long serialVersionUID = 1L;

    public VisitRecord(){

    }

    public VisitRecord(Integer pv, Integer uv, String date) {
        this.pv = pv;
        this.uv = uv;
        this.date = date;
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
     * 访问量
     */
    public Integer getPv() {
        return pv;
    }

    /**
     * 访问量
     */
    public void setPv(Integer pv) {
        this.pv = pv;
    }

    /**
     * 独立用户
     */
    public Integer getUv() {
        return uv;
    }

    /**
     * 独立用户
     */
    public void setUv(Integer uv) {
        this.uv = uv;
    }

    /**
     * 日期"02-23"
     */
    public String getDate() {
        return date;
    }

    /**
     * 日期"02-23"
     */
    public void setDate(String date) {
        this.date = date;
    }
}