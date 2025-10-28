package com.hs.util.image;

import com.hs.constants.exception.BadRequestException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 文件上传
 * @author Hs
 * @Date 2022/1/5 15:21
 */
@Component
public class UploadImageUtil {
    private static RestTemplate restTemplate = new RestTemplate();

    //GitHub上传文件API
    private static final String githubUploadApi = "https://api.github.com/repos/%s/%s/contents%s/%s";
    //GitHub上传文件API
    private static final String cdnUrl4Github = "https://fastly.jsdelivr.net/gh/%s/%s%s/%s";
    //没有Github配置时默认图片
    private static final String defaultImage = "https://cdn.jsdelivr.net/gh/iceswordhs/HsResource/Blog/comment/avatar/default_avatar.gif";

    private static final Logger log = LoggerFactory.getLogger(UploadImageUtil.class);

    ////服务访问地址，用于返回图片url
    //private static String serverUploadPath;
    ////服务器文件上传路径
    //private static String serverUrl;
    //GitHub token
    private static String githubToken;
    //GitHub用户名
    private static String githubUsername;
    //GitHub仓库名
    private static String githubRepos;
    //GitHub仓库路径
    private static String githubReposPath;

    //@Value("${upload.path}")
    //public void setServerUploadPath(String serverUploadPath) {
    //    UploadImageUtil.serverUploadPath = serverUploadPath;
    //}
    //
    //@Value("${custom.url.api}")
    //public void setServerUrl(String serverUrl) {
    //    UploadImageUtil.serverUrl = serverUrl;
    //}

    @Value("${upload.github.token}")
    public void setGithubToken(String githubToken) {
        UploadImageUtil.githubToken = githubToken;
    }

    @Value("${upload.github.username}")
    public void setGithubUsername(String githubUsername) {
        UploadImageUtil.githubUsername = githubUsername;
    }

    @Value("${upload.github.repos}")
    public void setGithubRepos(String githubRepos) {
        UploadImageUtil.githubRepos = githubRepos;
    }

    @Value("${upload.github.repos.path}")
    public void setGithubReposPath(String githubReposPath) {
        UploadImageUtil.githubReposPath = githubReposPath;
    }

    public static String pushBlogImageGithub(MultipartFile file) throws IOException {
        // 通过当前时间生成图片名
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String format = timeFormatter.format(time);
        String fileName = format+file.getOriginalFilename();
        String fileEncode = Base64.getEncoder().encodeToString(file.getBytes());

        String folderPath=githubReposPath+"/"+time.getYear()+"/"+time.getMonth().getValue();
        // 生成url
        String url=String.format(githubUploadApi,githubUsername,githubRepos,folderPath,fileName);
        // 生成带有token的请求头
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", Collections.singletonList("token "+githubToken));

        // 生成请求实体
        HashMap<String, String> body = new HashMap<>();
        body.put("message","添加博客内图片");
        body.put("content",fileEncode);

        // 生成请求
        HttpEntity<HashMap<String, String>> entity = new HttpEntity<>(body, headers);

        restTemplate.put(url,entity);

        // 返回url
        return String.format(cdnUrl4Github,githubUsername,githubRepos,folderPath,fileName);
    }


    static class ImageResource {
        byte[] data;
        //图片拓展名 jpg png
        String type;

        public byte[] getData() {
            return data;
        }

        public void setData(byte[] data) {
            this.data = data;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public ImageResource(byte[] data, String type) {
            this.data=data;
            this.type=type;
        }
    }

    /**
     * 通过url获取图片
     * @author huangshuai
     * @Date 2022/2/10 20:00
     * @param url url
     * @return com.hs.util.image.UploadImageUtil.ImageResource
     */
    public static ImageResource getImageByRequest(String url) {
        ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(url, byte[].class);
        if ("image".equals(responseEntity.getHeaders().getContentType().getType())) {
            return new ImageResource(responseEntity.getBody(), responseEntity.getHeaders().getContentType().getSubtype());
        }
        throw new BadRequestException("返回的类型不是图片");
    }

    //public static String saveImage(ImageResource image) throws IOException {
    //    File folder = new File(serverUploadPath);
    //    if (!folder.exists()) {
    //        folder.mkdirs();
    //    }
    //    String fileName = UUID.randomUUID() + "." + image.getType();
    //    FileOutputStream fileOutputStream = new FileOutputStream(serverUploadPath + fileName);
    //    fileOutputStream.write(image.getData());
    //    fileOutputStream.close();
    //    return serverUrl + "/image/" + fileName;
    //}

    /**
     * 将图片上传至github 上传QQ头像使用
     * @author huangshuai
     * @Date 2022/2/10 20:03
     * @param image 图片
     * @return java.lang.String
     */
    public static String push2Github(ImageResource image,String githubReposPath) {
        if (Boolean.FALSE.equals(checkGitConfig())) {
            log.info("github配置为空,头像设置为默认值");
            return defaultImage;
        }
        // 通过当前时间生成图片名
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String format = timeFormatter.format(time);
        String fileName = format+ "." + image.getType();
        String url = String.format(githubUploadApi, githubUsername, githubRepos, githubReposPath, fileName);
        String imgBase64 = Base64.getEncoder().encodeToString(image.getData());

        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", Collections.singletonList("token " + githubToken));

        HashMap<String, String> body = new HashMap<>();
        body.put("message", "Add comment avatar to HsResource");
        body.put("content", imgBase64);

        HttpEntity httpEntity = new HttpEntity(body, headers);
        restTemplate.put(url, httpEntity);

        return String.format(cdnUrl4Github, githubUsername, githubRepos, githubReposPath, fileName);
    }


    public static Boolean checkGitConfig(){
        return !StringUtils.isEmpty(githubToken) && !StringUtils.isEmpty(githubUsername)
                && !StringUtils.isEmpty(githubRepos) && !StringUtils.isEmpty(githubReposPath);
    }
}
