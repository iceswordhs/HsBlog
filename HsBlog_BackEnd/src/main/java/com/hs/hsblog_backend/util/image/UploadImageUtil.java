package com.hs.hsblog_backend.util.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
    private static final String cdnUrl4Github = "https://cdn.jsdelivr.net/gh/%s/%s%s/%s";

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
        System.out.println("注入githubToken"+githubToken);
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
        System.out.println(url);
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
}
