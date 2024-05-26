package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.ProjectOffer;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceProjectoffer {
    @Autowired
    private final JavaMailSender mailSender;

    @Autowired
    public EmailServiceProjectoffer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void send(String to, ProjectOffer projectOffer) {
        // Generate HTML content dynamically based on ProjectOffer attributes
        String htmlContent = generateHtmlContent(projectOffer);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(to);
            helper.setSubject("Project Offer Details");
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Method to generate HTML content dynamically based on ProjectOffer attributes
// Method to generate HTML content dynamically based on ProjectOffer attributes
    private String generateHtmlContent(ProjectOffer projectOffer) {
        // You can customize the HTML content generation based on your requirements
        StringBuilder htmlContentBuilder = new StringBuilder();

        htmlContentBuilder.append("<!DOCTYPE html>\n");
        htmlContentBuilder.append("<html lang=\"en\">\n");
        htmlContentBuilder.append("<head>\n");
        htmlContentBuilder.append("<meta charset=\"UTF-8\">\n");
        htmlContentBuilder.append("<title>").append(projectOffer.getProjectTitle()).append("</title>\n");
        htmlContentBuilder.append("</head>\n");
        htmlContentBuilder.append("<body>\n");
        htmlContentBuilder.append("<h1>").append(projectOffer.getProjectTitle()).append("</h1>\n");
        htmlContentBuilder.append("<p>Description: ").append(projectOffer.getDescription()).append("</p>\n");
        htmlContentBuilder.append("<p>Posted Date: ").append(projectOffer.getPostedDate()).append("</p>\n");
        htmlContentBuilder.append("<p>Company Name: ").append(projectOffer.getCompanyname()).append("</p>\n");
        htmlContentBuilder.append("<p>Company Email: ").append(projectOffer.getCompanyemail()).append("</p>\n");
        htmlContentBuilder.append("<p>Status: ").append(projectOffer.getStatus()).append("</p>\n");
        // Add more HTML content generation based on projectOffer attributes
        htmlContentBuilder.append("</body>\n");
        htmlContentBuilder.append("</html>\n");

        return htmlContentBuilder.toString();
    }
}