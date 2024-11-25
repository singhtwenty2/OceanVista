package com.singhtwenty2.OceanVistaBusiness.service.email;

import com.singhtwenty2.OceanVistaBusiness.util.template.EmailVerificationTemplate;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public CompletableFuture<Boolean> sendVerificationEmail(
            String senderEmail,
            String reciverEmail,
            String verificationLink
    ) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(senderEmail);
            helper.setTo(reciverEmail);
            helper.setSubject("Welcome to OceanVista - Please Verify Your Email");
            helper.setText(EmailVerificationTemplate.emailVerificationHTML(verificationLink), true);
            mailSender.send(mimeMessage);
            return CompletableFuture.completedFuture(true);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }
}
