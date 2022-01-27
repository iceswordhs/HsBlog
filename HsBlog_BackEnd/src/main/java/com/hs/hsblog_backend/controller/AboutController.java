package com.hs.hsblog_backend.controller;

import com.hs.hsblog_backend.entity.About;
import com.hs.hsblog_backend.service.AboutService;
import com.hs.hsblog_backend.util.Result;
import com.hs.hsblog_backend.util.commarkUtil.MarkDownToHTMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hs
 * @Date 2022/1/14 21:28
 */
@RestController
public class AboutController {
    @Autowired
    AboutService aboutService;

    @GetMapping("/about")
    public Result<About> about(){
        About aboutInfo = aboutService.getAboutInfo();
        aboutInfo.setContent(MarkDownToHTMLUtil.markdownToHtml(aboutInfo.getContent()));
        return Result.success(aboutInfo);
    }
}
