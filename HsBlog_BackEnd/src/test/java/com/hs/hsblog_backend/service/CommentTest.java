package com.hs.hsblog_backend.service;

import com.hs.hsblog_backend.constants.RedisKey;
import com.hs.hsblog_backend.entity.Comment;
import com.hs.hsblog_backend.util.StringUtils;
import com.hs.hsblog_backend.util.image.QQInfoUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Hs
 * @Date 2022/2/22 21:06
 */
@SpringBootTest
public class CommentTest {
    @Autowired
    RedisService redisService;

    // @Test
    void testComment(){
        Comment comment = new Comment();
        String commentNickname="2760463774";

        // 查询数据库，判断该QQ号的头像是否已上传
        // String urlByQQ = qqGbAvatarUrlMapper.getAvatarUrlByQQ(commentNickname);
        String urlByQQ = (String) redisService.getValueByHashKey(RedisKey.QQ_AVATAR_GITHUB_URL, commentNickname);
        System.out.println(urlByQQ);
        if (StringUtils.isEmpty(urlByQQ)){
            String avatar = QQInfoUtils.getQQAvatarURLByGithubUpload(commentNickname);
            comment.setAvatar(avatar);
            redisService.saveKVToHash(RedisKey.QQ_AVATAR_GITHUB_URL,commentNickname,avatar);
            //qqGbAvatarUrlMapper.saveQQAndAvatarUrl(commentNickname,avatar);
        } else {
            comment.setAvatar(urlByQQ);
        }
    }
}
