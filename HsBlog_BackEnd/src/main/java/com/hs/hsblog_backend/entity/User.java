package com.hs.hsblog_backend.entity;

import java.io.Serializable;

/**
 * User实体类
 * @author huangshuai
 * @Date 2021/11/30 14:11
 */
public class User implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户性别
     */
    private String gender;

    /**
     * 用户真名
     */
    private String trueName;

    /**
     * 用户生日
     */
    private String birthday;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 个人简介
     */
    private String personalBrief;

    /**
     * 头像图片url
     */
    private String avatarImgUrl;

    /**
     * 最近登录
     */
    private String recentlyLanded;

    /**
     * 权限
     */
    private String role;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     */
    public String getGender() {
        return gender;
    }

    /**
     * 
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 
     */
    public String getTrueName() {
        return trueName;
    }

    /**
     * 
     */
    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    /**
     * 
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     */
    public String getPersonalBrief() {
        return personalBrief;
    }

    /**
     * 
     */
    public void setPersonalBrief(String personalBrief) {
        this.personalBrief = personalBrief;
    }

    /**
     * 
     */
    public String getAvatarImgUrl() {
        return avatarImgUrl;
    }

    /**
     * 
     */
    public void setAvatarImgUrl(String avatarImgUrl) {
        this.avatarImgUrl = avatarImgUrl;
    }

    /**
     * 
     */
    public String getRecentlyLanded() {
        return recentlyLanded;
    }

    /**
     * 
     */
    public void setRecentlyLanded(String recentlyLanded) {
        this.recentlyLanded = recentlyLanded;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(){

    }

    public User(String username,String password){
        this.username=username;
        this.password=password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", phone=").append(phone);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", gender=").append(gender);
        sb.append(", trueName=").append(trueName);
        sb.append(", birthday=").append(birthday);
        sb.append(", email=").append(email);
        sb.append(", personalBrief=").append(personalBrief);
        sb.append(", avatarImgUrl=").append(avatarImgUrl);
        sb.append(", recentlyLanded=").append(recentlyLanded);
        sb.append(",role=").append(role);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}