package com.hs.hsblog_backend.service;

import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.entity.Moment;

import java.util.List;

/**
 * @author Hs
 * @Date 2022/1/17 15:33
 */
public interface MomentService {
    void createMoment(Moment moment);

    Moment findMomentById(Long id);

    PageInfo<Moment> findMomentByPageNum(Integer pageNum, Boolean adminIdentity);

    PageInfo<Moment> findMomentByPageNumAndPageSize(Integer pageNum,Integer PageSize);

    void updateMoment(Moment moment);

    void updateMomentLikes(Long id);

    void deleteMomentById(Long id);

    void updatePublishedById(Long id,Boolean published);
}
