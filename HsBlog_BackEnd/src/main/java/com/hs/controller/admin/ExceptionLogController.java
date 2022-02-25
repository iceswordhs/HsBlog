package com.hs.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.annotation.OperationAnnotation;
import com.hs.entity.NullObject;
import com.hs.entity.ExceptionLog;
import com.hs.service.ExceptionLogService;
import com.hs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hs
 * @Date 2022/2/6 21:04
 */
@RestController
@RequestMapping("/admin")
public class ExceptionLogController {
    @Autowired
    ExceptionLogService exceptionLogService;

    /**
     * 按时间分页查询异常日志
     * @author huangshuai
     * @Date 2022/2/6 21:06
     * @param date 时间
     * @param pageNum 页数
     * @param pageSize 每页大小
     * @return com.hs.util.Result<com.github.pagehelper.PageInfo<com.hs.entity.ExceptionLog>>
     */
    @GetMapping("/exceptionLogs")
    public Result<PageInfo<ExceptionLog>> exceptionLogs(@RequestParam(defaultValue = "") String[] date,
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
        PageInfo<ExceptionLog> pageInfo = new PageInfo<>(exceptionLogService.getExceptionLogListByDate(startDate, endDate));
        return Result.success(pageInfo);
    }

    /**
     * 按id删除异常日志
     * @author huangshuai
     * @Date 2022/2/6 21:07
     * @param id 异常日志id
     * @return com.hs.util.Result
     */
    @OperationAnnotation("根据Id删除操作日志")
    @DeleteMapping("/exceptionLog")
    public Result<NullObject> delete(@RequestParam Long id) {
        exceptionLogService.deleteExceptionLogById(id);
        return Result.success();
    }
}
