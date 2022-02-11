package com.hs.hsblog_backend.repository;

import com.hs.hsblog_backend.entity.About;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity com.hs.hsblog_backend.entity.About
 */
@Mapper
public interface AboutMapper {
    void insertAbout(About about);

    About findAbout();

    void updateAbout(About about);

    Boolean getAboutCommentEnabled();
}




