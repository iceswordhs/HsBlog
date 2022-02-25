package com.hs.repository;

import com.hs.entity.ExceptionLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.entity.ExceptionLog
 */
@Mapper
public interface ExceptionLogMapper {
    List<ExceptionLog> getExceptionLogListByDate(String startDate, String endDate);

    int saveExceptionLog(ExceptionLog log);

    int deleteExceptionLogById(Long id);
}




