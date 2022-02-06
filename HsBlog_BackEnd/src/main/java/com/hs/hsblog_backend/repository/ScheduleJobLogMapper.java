package com.hs.hsblog_backend.repository;

import com.hs.hsblog_backend.entity.ScheduleJobLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.hsblog_backend.entity.ScheduleJobLog
 */
@Mapper
public interface ScheduleJobLogMapper {
    List<ScheduleJobLog> getJobLogListByDate(String startDate, String endDate);

    int saveJobLog(ScheduleJobLog jobLog);

    int deleteJobLogByLogId(Long logId);
}




