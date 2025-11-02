package com.hs.task;

import com.hs.util.MailUtil;
import com.hs.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * @author Hs
 * @Date 2022/6/13 18:16
 */
/**
 * 每天发送一封邮件提醒自己是否完成习惯，并通过点击进行确认
 * @author huangshuai
 * @Date 2022/6/13 18:16
 * @return
 */
@Component
@PropertySource(value="classpath:application.properties",encoding = "utf-8")
public class DailyHabitEmailScheduleTask {
    @Autowired
    MailUtil mailUtil;

    @Value("${email.toAccount}")
    String toAccount;
    String subject="日常习惯提醒(早起流程)";

    public void sendDailyHabitMailScheduleTask(String template){
        // 如果是空或者包含html，那么就是早晨邮件
        if (StringUtils.isEmpty(template)||template.equals("reminderOfMorning.html")){
            mailUtil.sendHtmlTemplateMail(null, toAccount,subject,"reminderOfMorning.html");
        }
        else mailUtil.sendHtmlTemplateMail(null, toAccount,"夜晚总结与雷神的鼓舞",template);
    }
}
