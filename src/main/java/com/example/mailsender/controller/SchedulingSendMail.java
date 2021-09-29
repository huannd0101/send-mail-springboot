package com.example.mailsender.controller;

import com.example.mailsender.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@EnableAsync
@Component
@EnableScheduling
@ConditionalOnExpression("true")
public class SchedulingSendMail {

    @Autowired
    private JavaMailSender javaMailSender;

    private int cnt = 0;

    @Async
    @Scheduled(cron = "0 * * ? * *") //1 phút gửi 1 lần
    public void sendMailWithScheduling() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String htmlMsg = "<h3 style='color:red;'>Content width html</h3>"
                +"<img style='width:300px' src='https://res.cloudinary.com/dlqdesqni/image/upload/v1631257854/i7ujvuudc9pctszm48tn.jpg'>";

        message.setContent(htmlMsg, "text/html");
        helper.setSubject("This is subject " + cnt);
        helper.setTo(Constant.MAIL_TO);
        cnt++;

        javaMailSender.send(message);
        System.out.println("Send mail successfully");
    }
}
