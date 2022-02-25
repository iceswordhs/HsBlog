package com.hs.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.entity.NullObject;
import com.hs.entity.OperationLog;
import com.hs.service.OperationLogService;
import com.hs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hs
 * @Date 2022/2/6 21:16
 */
@RestController
@RequestMapping("/admin")
public class OperationLogController {
    @Autowired
    OperationLogService operationLogService;

    /**
     * 分页查询操作记录
     * @author huangshuai
     * @Date 2022/2/7 19:26
     * @param date 时间范围
     * @param pageNum 页数
     * @param pageSize 每页大小
     * @return com.hs.util.Result<com.github.pagehelper.PageInfo<com.hs.entity.OperationLog>>
     */
    @GetMapping("/operationLogs")
    public Result<PageInfo<OperationLog>> operationLogs(@RequestParam(defaultValue = "") String[] date,
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
        PageInfo<OperationLog> pageInfo = new PageInfo<>(operationLogService.getOperationLogListByDate(startDate, endDate));
        return Result.success(pageInfo);
    }

    /**
     * 按id删除操作日志
     * @author huangshuai
     * @Date 2022/2/7 19:27
     * @param id
     * @return com.hs.util.Result<com.hs.entity.NullObject>
     */
    @DeleteMapping("/operationLog")
    public Result<NullObject> delete(@RequestParam Long id) {
        operationLogService.deleteOperationLogById(id);
        return Result.success();
    }
}
