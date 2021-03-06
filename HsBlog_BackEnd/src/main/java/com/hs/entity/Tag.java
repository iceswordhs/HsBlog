package com.hs.entity;

import java.io.Serializable;

/**
 * 
 * @TableName tag
 */
public class Tag implements Serializable {
    /**
     * id
     */
    private Long tagId;

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

    public Tag(Long tagId,String tagName){
        this.tagId=tagId;
        this.tagName=tagName;
    }

    public Tag(Long tagId,String tagName,String color){
        this.tagId=tagId;
        this.tagName=tagName;
        this.color=color;
    }

    /**
     * id
     */
    public Long getTagId() {
        return tagId;
    }

    /**
     * id
     */
    public void setTagId(Long tagId) {
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

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}