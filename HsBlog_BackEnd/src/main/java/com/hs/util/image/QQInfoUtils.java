package com.hs.util.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

/**
 * @author Hs
 * @Date 2022/2/10 19:52
 */
@Component
public class QQInfoUtils {
    private static String commentQQImageReposPath;

    @Value("${upload.github.commentAvatar.repos.path}")
    public void setCommentQQImageReposPath(String path){
        QQInfoUtils.commentQQImageReposPath=path;
    }

    private static RestTemplate restTemplate = new RestTemplate();
    private static final String QQ_NICKNAME_URL = "https://r.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?uins={1}";
    private static final String QQ_AVATAR_URL = "https://q.qlogo.cn/g?b=qq&nk=%s&s=100";

    /**
     * 获取QQ昵称
     *
     * @param qq
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getQQNickname(String qq) throws UnsupportedEncodingException {
        String res = restTemplate.getForObject(QQ_NICKNAME_URL, String.class, qq);
        byte[] bytes = res.getBytes("iso-8859-1");
        String nickname = new String(bytes, "gb18030").split(",")[6].replace("\"", "");
        if ("".equals(nickname)) {
            return "nickname";
        }
        return nickname;
    }

    private static UploadImageUtil.ImageResource getImageResourceByQQ(String qq) {
        return UploadImageUtil.getImageByRequest(String.format(QQ_AVATAR_URL, qq));
    }

    ///**
    // * 将QQ头像下载到本地，并返回访问本地图片的URL
    // *
    // * @param qq
    // * @return 访问本地图片的URL
    // * @throws IOException
    // */
    //public static String getQQAvatarURLByServerUpload(String qq) throws IOException {
    //    return UploadImageUtil.saveImage(getImageResourceByQQ(qq));
    //}

    /**
     * 将QQ头像上传至GitHub仓库，并返回CDN链接
     *
     * @param qq
     * @return 指向该图片的jsDelivr CDN链接
     */
    public static String getQQAvatarURLByGithubUpload(String qq) {
        return UploadImageUtil.push2Github(getImageResourceByQQ(qq), commentQQImageReposPath);
    }

    public static boolean isQQNumber(String nickname) {
        return nickname.matches("^[1-9][0-9]{4,10}$");
    }
}
