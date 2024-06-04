package com.coconsult.pidevspring.RestControllers.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.EmailDetails;
import com.coconsult.pidevspring.DAO.Entities.EmailRequest;
import com.coconsult.pidevspring.Services.ProjectModule.EmailService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/mail")

public class EmailController {
  //  @Autowired
    private EmailService emailService;

    @PostMapping("/sendemail")
    public void sendEmail(@RequestBody EmailDetails emailDetails) {
      try {
        emailService.sendHtmlEmail(emailDetails.getTo(), emailDetails.getSubject(), emailDetails.getBody());
      } catch (MessagingException e) {
        e.printStackTrace(); // Gérer l'exception comme vous le souhaitez
      }
    }
  @PostMapping("/sendEmailWithAttachment")
  public ResponseEntity<String> sendEmailWithAttachment(@RequestBody EmailRequest request) {
    try {
      emailService.sendEmailWithAttachment(request.getRecipientEmail(), request.getAttachmentData(), request.getAttachmentName());
      return ResponseEntity.ok("E-mail envoyé avec succès !");
    } catch (MessagingException | IOException e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
    }
  }



}
