package com.hs.service;

import com.hs.entity.OperationLog;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @author Hs
 * @Date 2022/2/7 19:17
 */
public interface OperationLogService {
    List<OperationLog> getOperationLogListByDate(String startDate, String endDate);

    @Async
    void saveOperationLog(OperationLog log);

    void deleteOperationLogById(Long id);
}
