package com.hs.hsblog_backend.service.impl;

import com.hs.hsblog_backend.entity.ScheduleJob;
import com.hs.hsblog_backend.entity.ScheduleJobLog;
import com.hs.hsblog_backend.repository.ScheduleJobLogMapper;
import com.hs.hsblog_backend.repository.ScheduleJobMapper;
import com.hs.hsblog_backend.service.ScheduleJobService;
import com.hs.hsblog_backend.util.quartz.ScheduleUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Hs
 * @Date 2022/2/5 19:21
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {
    @Autowired
    Scheduler scheduler;
    @Autowired
    ScheduleJobMapper schedulerJobMapper;
    @Autowired
    ScheduleJobLogMapper scheduleJobLogMapper;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        List<ScheduleJob> scheduleJobList = getJobList();
        for (ScheduleJob scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public List<ScheduleJob> getJobList() {
        return schedulerJobMapper.getJobList();
    }

    @Override
    @Transactional
    public void saveJob(ScheduleJob scheduleJob) {
        if (schedulerJobMapper.saveJob(scheduleJob) != 1) {
            throw new PersistenceException("添加失败");
        }
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

    @Override
    @Transactional
    public void updateJob(ScheduleJob scheduleJob) {
        if (schedulerJobMapper.updateJob(scheduleJob) != 1) {
            throw new PersistenceException("更新失败");
        }
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
    }

    @Override
    @Transactional
    public void deleteJobById(Long jobId) {
        ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        if (schedulerJobMapper.deleteJobById(jobId) != 1) {
            throw new PersistenceException("删除失败");
        }
    }

    @Override
    public void runJobById(Long jobId) {
        ScheduleUtils.run(scheduler, schedulerJobMapper.getJobById(jobId));
    }

    @Override
    @Transactional
    public void updateJobStatusById(Long jobId, Boolean status) {
        if (status) {
            ScheduleUtils.resumeJob(scheduler, jobId);
        } else {
            ScheduleUtils.pauseJob(scheduler, jobId);
        }
        if (schedulerJobMapper.updateJobStatusById(jobId, status) != 1) {
            throw new PersistenceException("修改失败");
        }
    }

    @Override
    public List<ScheduleJobLog> getJobLogListByDate(String startDate, String endDate) {
        return scheduleJobLogMapper.getJobLogListByDate(startDate, endDate);
    }

    @Override
    @Transactional
    public void saveJobLog(ScheduleJobLog jobLog) {
        if (scheduleJobLogMapper.saveJobLog(jobLog) != 1) {
            throw new PersistenceException("日志添加失败");
        }
    }

    @Transactional
    @Override
    public void deleteJobLogByLogId(Long logId) {
        if (scheduleJobLogMapper.deleteJobLogByLogId(logId) != 1) {
            throw new PersistenceException("日志删除失败");
        }
    }
}
