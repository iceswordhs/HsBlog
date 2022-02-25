package com.hs.repository;

import com.hs.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.entity.OperationLog
 */
@Mapper
public interface OperationLogMapper {
    List<OperationLog> getOperationLogListByDate(String startDate, String endDate);

    int saveOperationLog(OperationLog log);

    int deleteOperationLogById(Long id);
}




