package com.hs.utils;

import com.hs.util.image.UploadImageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Hs
 * @Date 2022/1/5 17:38
 */
@SpringBootTest
public class TestUploadImageUtil {

    @Test
    void uploadImageUtil(){
        UploadImageUtil uploadImageUtil = new UploadImageUtil();
        //System.out.println(UploadImageUtil.pushBlogImageGithub());
    }
}
