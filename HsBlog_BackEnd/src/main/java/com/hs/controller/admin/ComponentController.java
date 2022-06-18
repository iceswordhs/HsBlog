package com.hs.controller.admin;

import com.hs.entity.NullObject;
import com.hs.model.vo.SignStatus;
import com.hs.repository.SignCalendarMapper;
import com.hs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Hs
 * @Date 2022/6/17 12:32
 */
@RestController
@RequestMapping("/admin")
public class ComponentController {
    @Autowired
    SignCalendarMapper mapper;

    @GetMapping("/signData")
    public Result<List<SignStatus>> signData(String firstDay){
        List<SignStatus> status = mapper.findDateSignStatus(firstDay);
        return Result.success(status);
    }

    @PostMapping("/sign")
    public Result<NullObject> sign(String today,Integer type){
        if (type!=2) mapper.morningSign(today);
        else mapper.nightSign(today);
        return Result.success();
    }
}
