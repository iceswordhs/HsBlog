package com.hs.hsblog_backend.repository;

import com.hs.hsblog_backend.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.hsblog_backend.entity.OperationLog
 */
@Mapper
public interface OperationLogMapper {
    List<OperationLog> getOperationLogListByDate(String startDate, String endDate);

    int saveOperationLog(OperationLog log);

    int deleteOperationLogById(Long id);
}




