package com.hs.entity;

import java.io.Serializable;

/**
 * 
 * @TableName site_setting
 */

public class SiteSetting implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 英文名
     */
    private String nameEn;

    /**
     * 中文名
     */
    private String nameZh;

    /**
     * 
     */
    private String value;

    /**
     * 1基础设置，2页脚徽标，3资料卡
     */
    private Integer type;

    private static final long serialVersionUID = 1L;

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
     * 
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * 
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    /**
     * 
     */
    public String getNameZh() {
        return nameZh;
    }

    /**
     * 
     */
    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    /**
     * 
     */
    public String getValue() {
        return value;
    }

    /**
     * 
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 1基础设置，2页脚徽标，3资料卡
     */
    public Integer getType() {
        return type;
    }

    /**
     * 1基础设置，2页脚徽标，3资料卡
     */
    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SiteSetting{" +
                "id=" + id +
                ", nameEn='" + nameEn + '\'' +
                ", nameZh='" + nameZh + '\'' +
                ", value='" + value + '\'' +
                ", type=" + type +
                '}';
    }
}