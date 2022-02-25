package com.hs.controller;

import com.hs.annotation.VisitLogger;
import com.hs.entity.About;
import com.hs.service.AboutService;
import com.hs.util.Result;
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

    @VisitLogger(behavior = "访问页面", content = "关于我")
    @GetMapping("/about")
    public Result<About> about(){
        About aboutInfo = aboutService.getAboutInfo();
        return Result.success(aboutInfo);
    }
}
