package com.hs.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.entity.NullObject;
import com.hs.annotation.OperationAnnotation;
import com.hs.entity.VisitLog;
import com.hs.service.VisitLogService;
import com.hs.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hs
 * @Date 2022/2/7 22:26
 */
@RestController
@RequestMapping("/admin")
public class VisitLogController {
    @Autowired
    VisitLogService visitLogService;

    /**
     * 分页查询访问日志列表
     * @author huangshuai
     * @Date 2022/2/7 22:27
     * @param uuid     按访客标识码模糊查询
     * @param date     按访问时间查询
     * @param pageNum  页码
     * @param pageSize 每页个数
     * @return com.hs.util.Result
     */
    @GetMapping("/visitLogs")
    public Result<PageInfo<VisitLog>> visitLogs(@RequestParam(defaultValue = "") String uuid,
                                                @RequestParam(defaultValue = "") String[] date,
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
        PageInfo<VisitLog> pageInfo = new PageInfo<>(visitLogService.getVisitLogListByUUIDAndDate(StringUtils.trim(uuid), startDate, endDate));
        return Result.success(pageInfo);
    }

    /**
     * 按Id删除访问日志
     * @author huangshuai
     * @Date 2022/2/7 22:28
     * @param id 日志Id
     * @return com.hs.util.Result
     */
    @OperationAnnotation("按Id删除访问日志")
    @DeleteMapping("/visitLog")
    public Result<NullObject> delete(@RequestParam Long id) {
        visitLogService.deleteVisitLogById(id);
        return Result.success();
    }
}
