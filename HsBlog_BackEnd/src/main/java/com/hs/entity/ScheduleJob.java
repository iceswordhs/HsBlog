package com.hs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName schedule_job
 */
public class ScheduleJob implements Serializable {
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY"; //任务调度参数key

    /**
     * 任务id
     */
    private Long jobId;

    /**
     * spring bean名称
     */
    private String beanName;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * cron表达式
     */
    private String cron;

    /**
     * 任务状态
     */
    private Boolean status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    public Long getJobId() {
        return jobId;
    }

    /**
     * 任务id
     */
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    /**
     * spring bean名称
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * spring bean名称
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    /**
     * 方法名
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 方法名
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * 参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 参数
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * cron表达式
     */
    public String getCron() {
        return cron;
    }

    /**
     * cron表达式
     */
    public void setCron(String cron) {
        this.cron = cron;
    }

    /**
     * 任务状态
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 任务状态
     */
    public void setStatus(Boolean status) {
        this.status = status;
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
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ScheduleJob{" +
                "jobId=" + jobId +
                ", beanName='" + beanName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", cron='" + cron + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}