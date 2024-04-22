package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.Services.HR.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailVerificationController {

    private final EmailVerificationService emailVerificationService;

    @Autowired
    public EmailVerificationController(EmailVerificationService emailVerificationService) {
        this.emailVerificationService = emailVerificationService;
    }

    @GetMapping("/verify/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email) {
        String verificationResult = emailVerificationService.verifyEmailAddress(email);
        if (verificationResult != null) {
            return new ResponseEntity<>(verificationResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to verify email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
