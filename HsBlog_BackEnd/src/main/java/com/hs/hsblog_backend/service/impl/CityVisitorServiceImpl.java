package com.hs.hsblog_backend.service.impl;

import com.hs.hsblog_backend.entity.CityVisitor;
import com.hs.hsblog_backend.repository.CityVisitorMapper;
import com.hs.hsblog_backend.service.CityVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hs
 * @Date 2022/2/8 21:19
 */
@Service
public class CityVisitorServiceImpl implements CityVisitorService {
    @Autowired
    CityVisitorMapper cityVisitorMapper;

    @Override
    public void saveCityVisitor(CityVisitor cityVisitor) {
        cityVisitorMapper.saveCityVisitor(cityVisitor);
    }
}
