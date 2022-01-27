package com.hs.hsblog_backend.service;

import com.hs.hsblog_backend.entity.Blog;
import com.hs.hsblog_backend.entity.Tag;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/12/16 17:50
 */
public interface TagService {
    /**
     * 更改tag颜色
     * @author huangshuai
     * @Date 2021/12/16 17:51
     * @param null
     * @return
     */
    void updateTagById(Tag tag);


    /**
     * 查询所有tag
     * @author huangshuai
     * @Date 2021/12/16 17:51
     * @param null
     * @return
     */
    List<Tag> getAllTags();

    /**
     * 根据tagid或tagName查询tag
     * @author huangshuai
     * @Date 2021/12/16 17:51
     * @param null
     * @return
     */
    Tag getTagByIdOrName(Tag tag);

    /**
     * 新建tag
     * @author huangshuai
     * @Date 2021/12/16 17:52
     * @param null
     * @return
     */
    Tag addTag(Tag tag);

    /**
     * 删除tag
     * @author huangshuai
     * @Date 2021/12/16 17:52
     * @param null
     * @return
     */
    void deleteTagById(Integer tagId);

    /**
     * 当保存博客的时候调用这个方法，保存新增的标签
     * @author huangshuai
     * @Date 2021/12/16 20:45
     * @param tags 博客的标签
     * @return void
     */
    List<Tag> saveNewBlogTags(List<Tag> tags);

    /**
     * 根据博客Id查询对应的tag
     * @author huangshuai
     * @Date 2021/12/27 14:38
     * @param blogId
     * @return java.util.List<com.hs.hsblog_backend.entity.Tag>
     */
    List<Tag> getTagByBlogId(Integer blogId);
}
