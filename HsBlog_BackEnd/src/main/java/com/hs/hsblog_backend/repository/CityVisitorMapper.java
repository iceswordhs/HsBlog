package com.hs.hsblog_backend.repository;

import com.hs.hsblog_backend.entity.CityVisitor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.hsblog_backend.entity.CityVisitor
 */
@Mapper
public interface CityVisitorMapper {
    List<CityVisitor> getCityVisitorList();

    int saveCityVisitor(CityVisitor cityVisitor);
}




