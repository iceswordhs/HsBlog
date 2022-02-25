package com.hs.repository;

import com.hs.entity.Moment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.entity.Moment
 */
@Mapper
public interface MomentMapper {
    void createMoment(Moment moment);

    Moment findMomentById(Long id);

    List<Moment> findAllMoment();

    void updateMomentContentAndPublished(Moment moment);

    void updatePublishedById(Long id,Boolean published);

    void updateLikes(int likes,Long id);

    void deleteMomentById(Long id);
}




