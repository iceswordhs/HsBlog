package com.hs.hsblog_backend.service.impl;

import com.hs.hsblog_backend.entity.About;
import com.hs.hsblog_backend.repository.AboutMapper;
import com.hs.hsblog_backend.service.AboutService;
import com.hs.hsblog_backend.util.commarkUtil.MarkDownToHTMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author Hs
 * @Date 2022/1/14 21:30
 */
@Service
public class AboutServiceImpl implements AboutService {
    @Autowired
    AboutMapper aboutMapper;

    @Override
    public About getAboutInfo() {
        return aboutMapper.findAbout();
    }

    @Override
    public void updateAbout(About about) {
        about.setUpdateTime(new Date());
        aboutMapper.updateAbout(about);
    }

    @Override
    public void insertAbout(About about) {
        aboutMapper.insertAbout(about);
    }
}
