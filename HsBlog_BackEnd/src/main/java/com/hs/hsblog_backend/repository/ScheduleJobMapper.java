package com.hs.hsblog_backend.repository;

import com.hs.hsblog_backend.entity.ScheduleJob;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.hsblog_backend.entity.ScheduleJob
 */
@Mapper
public interface ScheduleJobMapper {
    List<ScheduleJob> getJobList();

    ScheduleJob getJobById(Long jobId);

    int saveJob(ScheduleJob scheduleJob);

    int updateJob(ScheduleJob scheduleJob);

    int deleteJobById(Long jobId);

    int updateJobStatusById(Long jobId, Boolean status);
}




