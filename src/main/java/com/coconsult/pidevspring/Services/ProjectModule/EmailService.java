package com.coconsult.pidevspring.Services.ProjectModule;

import jakarta.mail.MessagingException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.InputStream;

@Service
@Component("EmailinvoiceService")
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired

    private JavaMailSender emailSender;

    public void sendHtmlEmail(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true); // true indicates multipart message

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true); // true indicates html

        javaMailSender.send(message);
    }


    public void sendEmailWithAttachment(String recipientEmail, byte[] attachmentData, String attachmentName) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setFrom("agriesprit3@gmail.com");
            helper.setTo(recipientEmail);
            helper.setSubject("Votre sujet");
            helper.setText("Votre message");

            helper.addAttachment(attachmentName, new ByteArrayResource(attachmentData));

            emailSender.send(message);
        } catch (MessagingException e) {
            // Gérer les exceptions liées à l'envoi du message
            throw new MessagingException("Erreur lors de l'envoi du message.", e);
        }
    }


}
