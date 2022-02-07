package com.hs.hsblog_backend.service;

import com.hs.hsblog_backend.entity.VisitLog;
import com.hs.hsblog_backend.model.dto.VisitLogUuidTime;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @author Hs
 * @Date 2022/2/7 22:21
 */
public interface VisitLogService {
    List<VisitLog> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate);

    List<VisitLogUuidTime> getUUIDAndCreateTimeByYesterday();

    @Async
    void saveVisitLog(VisitLog log);

    void deleteVisitLogById(Long id);
}
