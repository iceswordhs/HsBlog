package com.hs.hsblog_backend.service.impl;

import com.hs.hsblog_backend.entity.Tag;
import com.hs.hsblog_backend.repository.TagMapper;
import com.hs.hsblog_backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/12/16 17:50
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;

    @Override
    public void updateTagById(Tag tag) {
        tagMapper.updateTagById(tag);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagMapper.getAllTag();
    }

    @Override
    public Tag getTagByIdOrName(Tag tag) {
        return tagMapper.findByIdOrName(tag);
    }

    @Override
    public Integer addTag(Tag tag) {
        return tagMapper.addTag(tag);
    }

    @Override
    public void deleteTagById(Integer tagId) {
        tagMapper.deleteTagById(tagId);
    }

    @Override
    public List<Tag> saveNewBlogTags(List<Tag> tags){
        for (Tag tag:tags){
            Tag byIdOrName = tagMapper.findByIdOrName(tag);
            if (byIdOrName==null){
                // mybatis会自动将主键插入实体类
                if (tag.getColor()==null){
                    tag.setColor("red");
                }
                addTag(tag);
            }else tag.setTagId(byIdOrName.getTagId());
        }
        return tags;
    }

    @Override
    public List<Tag> getTagByBlogId(Integer blogId) {
        return tagMapper.getTagByBlogId(blogId);
    }
}
