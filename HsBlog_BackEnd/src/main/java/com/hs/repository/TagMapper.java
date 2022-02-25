package com.hs.repository;

import com.hs.model.vo.TagBlogCount;
import org.apache.ibatis.annotations.Mapper;
import com.hs.entity.Tag;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/12/16 17:19
 */
@Mapper
public interface TagMapper {
    void updateTagById(Tag tag);

    List<Tag> getAllTag();

    Tag findByIdOrName(Tag tag);

    void addTag(Tag tag);

    void deleteTagById(Long tagId);

    List<Tag> getTagByBlogId(Long blogId);

    List<TagBlogCount> getTagBlogCount();
}
