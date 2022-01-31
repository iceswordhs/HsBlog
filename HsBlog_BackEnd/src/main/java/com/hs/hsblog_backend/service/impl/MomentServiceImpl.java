package com.hs.hsblog_backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.entity.Moment;
import com.hs.hsblog_backend.repository.MomentMapper;
import com.hs.hsblog_backend.service.MomentService;
import com.hs.hsblog_backend.util.commarkUtil.MarkDownToHTMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hs
 * @Date 2022/1/17 22:46
 */
@Service
public class MomentServiceImpl implements MomentService {
    @Autowired
    MomentMapper momentMapper;
    //每页显示5条动态
    private static final int pageSize = 5;
    //动态列表排序方式
    private static final String orderBy = "create_time desc";
    //私密动态提示
    private static final String PRIVATE_MOMENT_CONTENT = "<p>此条为私密动态，仅发布者可见！</p>";

    @Override
    public void createMoment(Moment moment) {
        momentMapper.createMoment(moment);
    }

    @Override
    public Moment findMomentById(Long id) {
        return momentMapper.findMomentById(id);
    }

    @Override
    public PageInfo<Moment> findMomentByPageNum(Integer pageNum,Boolean adminIdentity) {
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<Moment> moments = momentMapper.findAllMoment();
        for (Moment moment : moments) {
            if (!moment.getPublished()) moment.setContent(PRIVATE_MOMENT_CONTENT);
            moment.setContent(MarkDownToHTMLUtil.markdownToHtml(moment.getContent()));
        }
        return new PageInfo<>(moments);
    }

    @Override
    public PageInfo<Moment> findMomentByPageNumAndPageSize(Integer pageNum, Integer PageSize) {
        PageHelper.startPage(pageNum,PageSize,"id desc");
        List<Moment> moments = momentMapper.findAllMoment();
        return new PageInfo<>(moments);
    }

    @Override
    public void updateMoment(Moment moment) {
        momentMapper.updateMomentContentAndPublished(moment);
    }

    @Override
    public void updateMomentLikes(Long id) {
        Moment moment = momentMapper.findMomentById(id);
        momentMapper.updateLikes(moment.getLikes()+1,id);
    }

    @Override
    public void deleteMomentById(Long id) {
        momentMapper.deleteMomentById(id);
    }

    @Override
    public void updatePublishedById(Long id, Boolean published) {
        momentMapper.updatePublishedById(id,published);
    }
}
