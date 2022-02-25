package com.hs.service;

import com.hs.entity.ScheduleJob;
import com.hs.entity.ScheduleJobLog;

import java.util.List;

/**
 * @author Hs
 * @Date 2022/2/5 19:20
 */
public interface ScheduleJobService {
    List<ScheduleJob> getJobList();

    void saveJob(ScheduleJob scheduleJob);

    void updateJob(ScheduleJob scheduleJob);

    void deleteJobById(Long jobId);

    void runJobById(Long jobId);

    void updateJobStatusById(Long jobId, Boolean status);

    List<ScheduleJobLog> getJobLogListByDate(String startDate, String endDate);

    void saveJobLog(ScheduleJobLog log);

    void deleteJobLogByLogId(Long logId);
}
