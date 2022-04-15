package com.kurumi.utils;

import com.kurumi.controller.ResObj;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

public class MailUtils {

    public static void generateMailCode(JavaMailSender javaMailSender, String username, String emailReceiver, String emailSender, HttpServletRequest request) {
        // 生成6为验证码
        String Captcha = String.valueOf(new Random().nextInt(899999) + 100000);
        System.out.println(username + "," + Captcha + "," + emailSender);
        request.getSession().setAttribute(username, Captcha);
//        System.out.println("session===" + request.getSession().getAttribute(username) + ",sessionID=" + request.getSession().getId());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            // 发送人邮件地址
            message.setFrom(emailSender);
            message.setTo(emailReceiver);
            message.setSubject("验证码");
            message.setText("接收到的验证码为：" + Captcha);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw e;
        }
    }
}
