package com.hs.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hs
 * @Date 2022/6/13 21:25
 */
@Component
@EnableAsync
public class MailUtil {
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private MailProperties mailProperties;
    @Autowired
    TemplateEngine templateEngine;

    @Async
    public void sendSimpleMail(String toAccount, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailProperties.getUsername());
            message.setTo(toAccount);
            message.setSubject(subject);
            message.setText(content);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public void sendHtmlTemplateMail(String toAccount, String subject, String template) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,"UTF-8");
            Context context = new Context();
            context.setVariables(new HashMap<>());
            String process = templateEngine.process(template,context);
            messageHelper.setFrom(mailProperties.getUsername());
            messageHelper.setTo(toAccount);
            messageHelper.setSubject(subject);
            messageHelper.setText(process, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
