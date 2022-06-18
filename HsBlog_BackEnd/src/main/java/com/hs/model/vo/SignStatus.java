package com.hs.model.vo;

/**
 * @author Hs
 * @Date 2022/6/17 13:01
 */
public class SignStatus {
    Integer morningStatus;
    Integer nightStatus;

    public SignStatus(){}

    public SignStatus(Integer morningStatus,Integer nightStatus){
        this.morningStatus=morningStatus;
        this.nightStatus=nightStatus;
    }

    public Integer getMorningStatus() {
        return morningStatus;
    }

    public void setMorningStatus(Integer morningStatus) {
        this.morningStatus = morningStatus;
    }

    public Integer getNightStatus() {
        return nightStatus;
    }

    public void setNightStatus(Integer nightStatus) {
        this.nightStatus = nightStatus;
    }

    @Override
    public String toString() {
        return "SignStatus{" +
                "morningStatus=" + morningStatus +
                ", nightStatus=" + nightStatus +
                '}';
    }
}
