package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.TrainingSession;
import com.coconsult.pidevspring.Services.TrainingSession.ITrainingSessionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/TrainingSession-TrainingSession")
@AllArgsConstructor
public class TrainingSessionRestController {
    private final ITrainingSessionService iTrainingSessionService;
    private static final Logger logger = LoggerFactory.getLogger(TrainingSessionRestController.class); // Logger added here

    @GetMapping("/findOneTrainingSession/{TS_id}")
    public TrainingSession findOneTrainingSession(@PathVariable("TS_id") Long TS_id) {
        return iTrainingSessionService.findOneTrainingSession(TS_id);
    }

    @GetMapping("/findAllTrainingSession")
    public List<TrainingSession> findAllActivities() {
        return iTrainingSessionService.findAllTrainingSession();
    }
    @PostMapping("/addTrainingSession")
    public ResponseEntity<?> addTrainingSession(@RequestBody TrainingSession trainingSession) {
        logger.info("Received request to add new session: {}", trainingSession);
        try {
            TrainingSession createdSession = iTrainingSessionService.addTrainingSession(trainingSession);
            return ResponseEntity.ok(createdSession);
        } catch (Exception e) {
            logger.error("Error saving training session", e);
            return ResponseEntity.badRequest().body("Failed to save training session: " + e.getMessage());
        }
    }

    @PutMapping("/UpdateTrainingSession")
    public TrainingSession UpdateTrainingSession(@RequestBody TrainingSession trainingSession) {
        logger.debug("Updating training session: {}", trainingSession);
        return iTrainingSessionService.UpdateTrainingSession(trainingSession);
    }

    @DeleteMapping("/deleteTrainingSessionById/{TS_id}")
    public void deleteTrainingSessionById(@PathVariable("TS_id") Long TS_id) {
        logger.debug("Deleting training session with ID: {}", TS_id);
        iTrainingSessionService.deleteTrainingSessionById(TS_id);
    }
}
