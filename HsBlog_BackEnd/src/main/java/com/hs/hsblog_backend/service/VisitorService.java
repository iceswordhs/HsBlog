package com.hs.hsblog_backend.service;

import com.hs.hsblog_backend.entity.Visitor;
import com.hs.hsblog_backend.model.dto.VisitLogUuidTime;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @author Hs
 * @Date 2022/2/7 22:39
 */
public interface VisitorService {
    List<Visitor> getVisitorListByDate(String startDate, String endDate);

    List<String> getNewVisitorIpSourceByYesterday();

    boolean hasUUID(String uuid);

    @Async
    void saveVisitor(Visitor visitor);

    void updatePVAndLastTimeByUUID(VisitLogUuidTime dto);

    void deleteVisitor(Long id, String uuid);
}
