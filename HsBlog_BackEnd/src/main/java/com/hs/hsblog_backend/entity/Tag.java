package com.hs.hsblog_backend.entity;

import java.io.Serializable;

/**
 * 
 * @TableName tag
 */
public class Tag implements Serializable {
    /**
     * id
     */
    private Integer tagId;

    /**
     * 标签名
     */
    private String tagName;

    /**
     * 颜色，与semanticUI对应
     */
    private String color;

    private static final long serialVersionUID = 1L;

    public Tag(){

    }

    public Tag(String tagName){
        this.tagName=tagName;
    }

    public Tag(Integer tagId,String tagName){
        this.tagId=tagId;
        this.tagName=tagName;
    }

    public Tag(Integer tagId,String tagName,String color){
        this.tagId=tagId;
        this.tagName=tagName;
        this.color=color;
    }

    /**
     * id
     */
    public Integer getTagId() {
        return tagId;
    }

    /**
     * id
     */
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    /**
     * 标签名
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 标签名
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /**
     * 颜色，与semanticUI对应
     */
    public String getColor() {
        return color;
    }

    /**
     * 颜色，与semanticUI对应
     */
    public void setColor(String color) {
        this.color = color;
    }
}