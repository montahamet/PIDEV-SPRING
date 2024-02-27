package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.Services.TrainingSession.IRegistrationTSService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegistrationTSRestController {
    IRegistrationTSService iRegistrationTSService;
}
