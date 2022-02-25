package com.hs.entity;

import java.io.Serializable;

/**
 * 
 * @TableName city_visitor
 */
public class CityVisitor implements Serializable {
    /**
     * 城市名称
     */
    private String city;

    /**
     * 独立访客数量
     */
    private Integer uv;

    private static final long serialVersionUID = 1L;

    public CityVisitor(String city, Integer uv) {
        this.city=city;
        this.uv=uv;
    }

    /**
     * 城市名称
     */
    public String getCity() {
        return city;
    }

    /**
     * 城市名称
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 独立访客数量
     */
    public Integer getUv() {
        return uv;
    }

    /**
     * 独立访客数量
     */
    public void setUv(Integer uv) {
        this.uv = uv;
    }
}