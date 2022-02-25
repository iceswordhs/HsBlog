package com.hs.model.dto;

import java.util.Date;

/**
 * @author Hs
 * @Date 2022/2/7 22:16
 */
public class VisitLogUuidTime {
    private String uuid;
    private Date time;
    private Integer pv;

    public VisitLogUuidTime(){

    }

    public VisitLogUuidTime(String uuid, Date date, Integer integer) {
        this.uuid=uuid;
        this.time=date;
        this.pv=integer;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }
}
