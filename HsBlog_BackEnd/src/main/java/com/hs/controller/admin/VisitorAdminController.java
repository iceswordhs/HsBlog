package com.hs.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.entity.Visitor;
import com.hs.service.VisitorService;
import com.hs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hs
 * @Date 2022/2/8 20:49
 */
@RestController
@RequestMapping("/admin")
public class VisitorAdminController {
    @Autowired
    VisitorService visitorService;

    /**
     * 分页查询访问者
     * @author huangshuai
     * @Date 2022/2/8 20:51
     * @param date
     * @param pageNum
     * @param pageSize
     * @return com.hs.util.Result
     */
    @GetMapping("/visitors")
    public Result visitors(@RequestParam(defaultValue = "") String[] date,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
        String startDate = null;
        String endDate = null;
        if (date.length == 2) {
            startDate = date[0];
            endDate = date[1];
        }
        String orderBy = "create_time desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<Visitor> pageInfo = new PageInfo<>(visitorService.getVisitorListByDate(startDate, endDate));
        return Result.success(pageInfo);
    }

    /**
     * 按Id删除访客
     * @author huangshuai
     * @Date 2022/2/8 20:51
     * @param id
     * @param uuid
     * @return com.hs.util.Result
     */
    @DeleteMapping("/visitor")
    public Result delete(@RequestParam Long id, @RequestParam String uuid) {
        visitorService.deleteVisitor(id, uuid);
        return Result.success();
    }
}
