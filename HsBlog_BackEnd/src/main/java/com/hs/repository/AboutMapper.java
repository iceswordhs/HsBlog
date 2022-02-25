package com.hs.repository;

import com.hs.entity.About;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity com.hs.entity.About
 */
@Mapper
public interface AboutMapper {
    void insertAbout(About about);

    About findAbout();

    void updateAbout(About about);

    Boolean getAboutCommentEnabled();
}




