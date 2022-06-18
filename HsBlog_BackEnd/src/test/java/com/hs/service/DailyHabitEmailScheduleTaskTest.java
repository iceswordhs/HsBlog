package com.hs.service;

import com.hs.task.DailyHabitEmailScheduleTask;
import com.hs.util.MailUtil;
import com.hs.util.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Hs
 * @Date 2022/6/13 22:06
 */
@SpringBootTest
public class DailyHabitEmailScheduleTaskTest {
    @Autowired
    DailyHabitEmailScheduleTask emailScheduleTask;
    @Autowired
    MailUtil mailUtil;

    @Test
    void testSendEmail(){
        //emailScheduleTask.sendDailyHabitMailScheduleTask("");
        //mailUtil.sendHtmlTemplateMail("2760463774@qq.com","提醒","reminderOfMorning.html");
        emailScheduleTask.sendDailyHabitMailScheduleTask("reminderOfMorning.html");
    }
}
