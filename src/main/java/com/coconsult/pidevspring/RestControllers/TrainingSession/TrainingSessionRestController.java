package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.Services.TrainingSession.ITrainingSessionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TrainingSessionRestController {
    ITrainingSessionService iTrainingSessionService;
}
