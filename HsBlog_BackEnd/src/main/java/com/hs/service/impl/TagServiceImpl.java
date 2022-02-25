package com.hs.service.impl;

import com.hs.constants.RedisKey;
import com.hs.entity.Tag;
import com.hs.repository.TagMapper;
import com.hs.service.RedisService;
import com.hs.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author Hs
 * @Date 2021/12/16 17:50
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;
    @Autowired
    RedisService redisService;

    @Override
    public void updateTagById(Tag tag) {
        redisService.deleteCacheByKey(RedisKey.TAG_CLOUD_LIST);
        //修改了标签名或颜色，可能有首页文章关联了标签，也要更新首页缓存
        redisService.deleteCacheByKey(RedisKey.HOME_BLOG_INFO_LIST);
        tagMapper.updateTagById(tag);
    }

    @Override
    public List<Tag> getAllTags() {
        String tagCloudList = RedisKey.TAG_CLOUD_LIST;
        List<Tag> listByValue = redisService.getListByValue(tagCloudList);
        if (listByValue!=null){
            return listByValue;
        }
        List<Tag> allTag = tagMapper.getAllTag();
        redisService.saveListToValue(tagCloudList,allTag);
        return allTag;
    }

    @Override
    public Tag getTagByIdOrName(Tag tag) {
        return tagMapper.findByIdOrName(tag);
    }

    @Override
    public void addTag(Tag tag) {
        redisService.deleteCacheByKey(RedisKey.TAG_CLOUD_LIST);
        tagMapper.addTag(tag);
    }

    @Override
    public void deleteTagById(Long tagId) {
        redisService.deleteCacheByKey(RedisKey.TAG_CLOUD_LIST);
        tagMapper.deleteTagById(tagId);
    }

    @Override
    public List<Tag> saveNewBlogTags(List<Tag> tags){
        String[] colors=new String[]{"red","orange","yellow","olive","green","teal","blue","violet","purple","pink","brown","grey","black"};
        for (Tag tag:tags){
            Tag byIdOrName = tagMapper.findByIdOrName(tag);
            if (byIdOrName==null){
                if (tag.getColor()==null){
                    Random random = new Random();
                    int colorRandomInt = random.nextInt(colors.length);
                    tag.setColor(colors[colorRandomInt]);
                }
                // mybatis会自动将主键插入实体类
                addTag(tag);
            }else tag.setTagId(byIdOrName.getTagId());
        }
        return tags;
    }

    @Override
    public List<Tag> getTagByBlogId(Long blogId) {
        return tagMapper.getTagByBlogId(blogId);
    }
}
