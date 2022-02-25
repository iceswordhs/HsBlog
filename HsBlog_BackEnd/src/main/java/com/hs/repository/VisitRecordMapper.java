package com.hs.repository;

import com.hs.entity.VisitRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.entity.VisitRecord
 */
@Mapper
public interface VisitRecordMapper {
    List<VisitRecord> getVisitRecordListByLimit(Integer limit);

    int saveVisitRecord(VisitRecord visitRecord);
}




