package com.hs.hsblog_backend.repository;

import com.hs.hsblog_backend.entity.VisitLog;
import com.hs.hsblog_backend.model.dto.VisitLogUuidTime;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.hsblog_backend.entity.VisitLog
 */
@Mapper
public interface VisitLogMapper {
    List<VisitLog> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate);

    List<VisitLogUuidTime> getUUIDAndCreateTimeByYesterday();

    int saveVisitLog(VisitLog log);

    int deleteVisitLogById(Long id);

    int countVisitLogByToday();
}




