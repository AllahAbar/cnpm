package com.example.hello.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        try {
            // Ghi log chi tiết email trước khi gửi
            logger.info("Đang cố gắng gửi email tới: {}, Chủ đề: {}", to, subject);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("nguyenhoang31102002@gmail.com"); // Quan trọng: Đặt email người gửi
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            
            mailSender.send(message);
            
            logger.info("Đã gửi email thành công tới: {}", to);
        } catch (Exception e) {
            logger.error("Gửi email thất bại tới: {}", to, e);
            throw new RuntimeException("Gửi email thất bại: " + e.getMessage(), e);
        }
    }
}