package com.hs.hsblog_backend.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName schedule_job_log
 */
public class ScheduleJobLog implements Serializable {
    /**
     * 任务日志id
     */
    private Long logId;

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
     * 任务执行结果
     */
    private Boolean status;

    /**
     * 异常信息
     */
    private String error;

    /**
     * 耗时（单位：毫秒）
     */
    private Integer times;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 任务日志id
     */
    public Long getLogId() {
        return logId;
    }

    /**
     * 任务日志id
     */
    public void setLogId(Long logId) {
        this.logId = logId;
    }

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
     * 任务执行结果
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 任务执行结果
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 异常信息
     */
    public String getError() {
        return error;
    }

    /**
     * 异常信息
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * 耗时（单位：毫秒）
     */
    public Integer getTimes() {
        return times;
    }

    /**
     * 耗时（单位：毫秒）
     */
    public void setTimes(Integer times) {
        this.times = times;
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
        return "ScheduleJobLog{" +
                "logId=" + logId +
                ", jobId=" + jobId +
                ", beanName='" + beanName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", times=" + times +
                ", createTime=" + createTime +
                '}';
    }
}