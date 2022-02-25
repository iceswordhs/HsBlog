package com.hs.controller.admin;

import com.hs.util.Result;
import com.hs.util.image.UploadImageUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Hs
 * @Date 2022/1/5 14:40
 */
@RestController
@RequestMapping("/admin")
public class ImageController {
    @RequestMapping("/uploadImage")
    public Result<String> UploadImage(@RequestPart("image") MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        // https://cdn.jsdelivr.net/gh/Naccl/blog-resource/NBlogApp/img/avatar.jpg
        // https://cdn.jsdelivr.net/gh/iceswordhs/HsResource/Blog/image/test1.jpg
        return Result.success(UploadImageUtil.pushBlogImageGithub(file));
    }
}
