package com.green.firstproject.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailServiceImpl {

    public void sendEmail(String to, String subject, String content, String provider) {
        try {
            JavaMailSenderImpl mailSender = createMailSender(provider);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);

            mailSender.send(message);
            System.out.println("이메일이 성공적으로 전송되었습니다: " + to);
        } catch (Exception e) {
            System.err.println("메일 발송 실패: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("메일 발송 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    private JavaMailSenderImpl createMailSender(String provider) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties props = mailSender.getJavaMailProperties();

        switch (provider.toLowerCase()) {
            case "gmail":
                configureMailSender(mailSender, "smtp.gmail.com", 587,
                        System.getenv("GMAIL_USERNAME"), System.getenv("GMAIL_PASSWORD"), true);
                break;

            case "naver":
                configureMailSender(mailSender, "smtp.naver.com", 465,
                        System.getenv("NAVER_USERNAME"), System.getenv("NAVER_PASSWORD"), false);
                break;

            default:
                throw new IllegalArgumentException("Invalid email provider: " + provider);
        }
        return mailSender;
    }

    private void configureMailSender(JavaMailSenderImpl mailSender, String host, int port,
                                     String username, String password, boolean useTLS) {
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        if (useTLS) {
            props.put("mail.smtp.starttls.enable", "true");
        } else {
            props.put("mail.smtp.ssl.enable", "true");
        }
    }
}