package com.coconsult.pidevspring.RestControllers.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.EmailDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class EmailController {
//    @Autowired
//    private EmailService emailService;

//    @PostMapping("/sendemail")
//    public void sendEmail(@RequestBody EmailDetails emailDetails) {
//        emailService.sendSimpleEmail(emailDetails.getTo(), emailDetails.getSubject(), emailDetails.getBody());
//    }
}
