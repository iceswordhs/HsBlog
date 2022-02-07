package com.hs.hsblog_backend.repository;

import com.hs.hsblog_backend.entity.VisitRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.hsblog_backend.entity.VisitRecord
 */
@Mapper
public interface VisitRecordMapper {
    List<VisitRecord> getVisitRecordListByLimit(Integer limit);

    int saveVisitRecord(VisitRecord visitRecord);
}




