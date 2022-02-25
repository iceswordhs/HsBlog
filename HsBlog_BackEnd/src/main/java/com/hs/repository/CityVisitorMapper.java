package com.hs.repository;

import com.hs.entity.CityVisitor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.entity.CityVisitor
 */
@Mapper
public interface CityVisitorMapper {
    List<CityVisitor> getCityVisitorList();

    int saveCityVisitor(CityVisitor cityVisitor);
}




