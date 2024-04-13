package com.coconsult.pidevspring.RestControllers.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.EmailDetails;
import com.coconsult.pidevspring.Services.ProjectModule.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        emailService.sendSimpleEmail(emailDetails.getTo(), emailDetails.getSubject(), emailDetails.getBody());
    }
}
