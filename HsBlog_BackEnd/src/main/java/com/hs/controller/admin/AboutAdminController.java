package com.hs.controller.admin;

import com.hs.annotation.OperationAnnotation;
import com.hs.entity.About;
import com.hs.entity.NullObject;
import com.hs.service.AboutService;
import com.hs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hs
 * @Date 2022/1/17 9:21
 */
@RequestMapping("/admin")
@RestController
public class AboutAdminController {
    @Autowired
    AboutService aboutService;

    @RequestMapping("/about")
    public Result<About> getAbout(){
        return Result.success(aboutService.getAboutInfoManage());
    }

    @OperationAnnotation("修改关于我页面")
    @PostMapping("/updateAbout")
    public Result<NullObject> updateAbout(@RequestBody About about){
        aboutService.updateAbout(about);
        return Result.success();
    }
}
