package com.hs.repository;

import com.hs.entity.ScheduleJobLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.entity.ScheduleJobLog
 */
@Mapper
public interface ScheduleJobLogMapper {
    List<ScheduleJobLog> getJobLogListByDate(String startDate, String endDate);

    int saveJobLog(ScheduleJobLog jobLog);

    int deleteJobLogByLogId(Long logId);
}




