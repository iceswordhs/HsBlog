package com.hs.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.annotation.OperationAnnotation;
import com.hs.entity.ScheduleJob;
import com.hs.entity.ScheduleJobLog;
import com.hs.service.ScheduleJobService;
import com.hs.util.Result;
import com.hs.util.commarkUtil.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Hs
 * @Date 2022/2/6 13:15
 */
@RestController
@RequestMapping("/admin")
public class ScheduleController {
    @Autowired
    ScheduleJobService service;

    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 分页查询定时任务列表
     *
     * @param pageNum  页码
     * @param pageSize 每页条数
     * @return
     */
    @GetMapping("/jobs")
    public Result jobs(@RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<ScheduleJob> pageInfo = new PageInfo<>(scheduleJobService.getJobList());
        return Result.success(pageInfo);
    }

    /**
     * 新建定时任务
     *
     * @param scheduleJob
     * @return
     */
    @OperationAnnotation("新建定时任务")
    @PostMapping("/job")
    public Result saveJob(@RequestBody ScheduleJob scheduleJob) {
        scheduleJob.setStatus(false);
        scheduleJob.setCreateTime(new Date());
        ValidatorUtils.validateEntity(scheduleJob);
        scheduleJobService.saveJob(scheduleJob);
        return Result.success();
    }

    /**
     * 修改定时任务
     *
     * @param scheduleJob
     * @return
     */
    @OperationAnnotation("修改定时任务")
    @PutMapping("/job")
    public Result updateJob(@RequestBody ScheduleJob scheduleJob) {
        scheduleJob.setStatus(false);
        ValidatorUtils.validateEntity(scheduleJob);
        scheduleJobService.updateJob(scheduleJob);
        return Result.success();
    }

    /**
     * 删除定时任务
     *
     * @param jobId 任务id
     * @return
     */
    @OperationAnnotation("删除定时任务")
    @DeleteMapping("/job")
    public Result deleteJob(@RequestParam Long jobId) {
        scheduleJobService.deleteJobById(jobId);
        return Result.success();
    }

    /**
     * 立即执行任务
     *
     * @param jobId 任务id
     * @return
     */
    @OperationAnnotation("立即执行定时任务")
    @PostMapping("/job/run")
    public Result runJob(@RequestParam Long jobId) {
        scheduleJobService.runJobById(jobId);
        return Result.success();
    }

    /**
     * 更新任务状态：暂停或恢复
     *
     * @param jobId  任务id
     * @param status 状态
     * @return
     */
    @OperationAnnotation("更新任务状态")
    @PutMapping("/job/status")
    public Result updateJobStatus(@RequestParam Long jobId, @RequestParam Boolean status) {
        scheduleJobService.updateJobStatusById(jobId, status);
        return Result.success();
    }

    /**
     * 分页查询定时任务日志列表
     *
     * @param date     按执行时间查询
     * @param pageNum  页码
     * @param pageSize 每页条数
     * @return
     */
    @GetMapping("/job/logs")
    public Result logs(@RequestParam(defaultValue = "") String[] date,
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
        PageInfo<ScheduleJobLog> pageInfo = new PageInfo<>(scheduleJobService.getJobLogListByDate(startDate, endDate));
        return Result.success(pageInfo);
    }

    /**
     * 按id删除任务日志
     *
     * @param logId 日志id
     * @return
     */
    @OperationAnnotation("按id删除任务日志")
    @DeleteMapping("/job/log")
    public Result delete(@RequestParam Long logId) {
        scheduleJobService.deleteJobLogByLogId(logId);
        return Result.success();
    }
}
