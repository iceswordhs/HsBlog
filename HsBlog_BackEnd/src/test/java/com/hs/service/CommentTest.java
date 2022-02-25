package com.hs.service;

import com.hs.constants.RedisKey;
import com.hs.entity.Comment;
import com.hs.util.StringUtils;
import com.hs.util.image.QQInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        String urlByQQ = (String) redisService.getValueByHashKey(RedisKey.QQ_AVATAR_GITHUB_URL, commentNickname);
        System.out.println(urlByQQ);
        if (StringUtils.isEmpty(urlByQQ)){
            String avatar = QQInfoUtils.getQQAvatarURLByGithubUpload(commentNickname);
            comment.setAvatar(avatar);
            redisService.saveKVToHash(RedisKey.QQ_AVATAR_GITHUB_URL,commentNickname,avatar);
        } else {
            comment.setAvatar(urlByQQ);
        }
    }
}
