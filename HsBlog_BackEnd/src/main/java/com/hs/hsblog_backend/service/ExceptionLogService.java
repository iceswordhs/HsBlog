package com.hs.hsblog_backend.service;

import com.hs.hsblog_backend.entity.ExceptionLog;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @author Hs
 * @Date 2022/2/6 19:03
 */
public interface ExceptionLogService {
    List<ExceptionLog> getExceptionLogListByDate(String startDate, String endDate);

    @Async
    void saveExceptionLog(ExceptionLog log);

    void deleteExceptionLogById(Long id);
}
