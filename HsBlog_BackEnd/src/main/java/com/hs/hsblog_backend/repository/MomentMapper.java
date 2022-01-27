package com.hs.hsblog_backend.repository;

import com.hs.hsblog_backend.entity.Moment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.hsblog_backend.entity.Moment
 */
@Mapper
public interface MomentMapper {
    void createMoment(Moment moment);

    Moment findMomentById(Integer id);

    List<Moment> findAllMoment();

    void updateMomentContentAndPublished(Moment moment);

    void updatePublishedById(Integer id,Boolean published);

    void updateLikes(int likes,Integer id);

    void deleteMomentById(Integer id);
}




