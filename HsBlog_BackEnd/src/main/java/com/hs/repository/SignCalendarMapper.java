package com.hs.repository;


import com.hs.model.vo.SignStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.hsblog_backend.entity.SignCalendar
 */
@Mapper
public interface SignCalendarMapper {
    List<SignStatus> findDateSignStatus(String firstDay);

    void morningSign(String today);

    void nightSign(String today);
}




