package com.hs.hsblog_backend.service.impl;

import com.hs.hsblog_backend.entity.VisitRecord;
import com.hs.hsblog_backend.repository.VisitRecordMapper;
import com.hs.hsblog_backend.service.VisitRecordService;
import com.hs.hsblog_backend.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hs
 * @Date 2022/2/8 21:15
 */
@Service
public class VisitRecordServiceImpl implements VisitRecordService {
    @Autowired
    VisitRecordMapper visitRecordMapper;

    @Override
    public void saveVisitRecord(VisitRecord visitRecord) {
        visitRecordMapper.saveVisitRecord(visitRecord);
    }
}
