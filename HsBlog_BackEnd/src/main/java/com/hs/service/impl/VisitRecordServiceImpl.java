package com.hs.service.impl;

import com.hs.entity.VisitRecord;
import com.hs.repository.VisitRecordMapper;
import com.hs.service.VisitRecordService;
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
