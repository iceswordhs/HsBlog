package com.hs.service.impl;

import com.hs.entity.CityVisitor;
import com.hs.repository.CityVisitorMapper;
import com.hs.service.CityVisitorService;
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
