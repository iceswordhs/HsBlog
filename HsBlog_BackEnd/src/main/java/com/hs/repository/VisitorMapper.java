package com.hs.repository;

import com.hs.entity.Visitor;
import com.hs.model.dto.VisitLogUuidTime;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.entity.Visitor
 */
@Mapper
public interface VisitorMapper {
    List<Visitor> getVisitorListByDate(String startDate, String endDate);

    List<String> getNewVisitorIpSourceByYesterday();

    int hasUUID(String uuid);

    int saveVisitor(Visitor visitor);

    int updatePVAndLastTimeByUUID(VisitLogUuidTime dto);

    int deleteVisitorById(Long id);
}




