package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Room;
import com.coconsult.pidevspring.DAO.Entities.TrainingSession;
import com.coconsult.pidevspring.Services.TrainingSession.IRoomService;
import com.coconsult.pidevspring.Services.TrainingSession.ITrainingSessionService;
import com.coconsult.pidevspring.Services.TrainingSession.RoomService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/TrainingSession-TrainingSession")
@AllArgsConstructor
public class TrainingSessionRestController {
    private final ITrainingSessionService iTrainingSessionService;
    IRoomService roomService;
    private static final Logger logger = LoggerFactory.getLogger(TrainingSessionRestController.class); // Logger added here

    @GetMapping("/findOneTrainingSession/{ts_id}")
    public ResponseEntity<TrainingSession> findOneTrainingSession(@PathVariable Long ts_id) {
        TrainingSession dto = iTrainingSessionService.findOneTrainingSession(ts_id);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/findAllTrainingSession")
    public List<TrainingSession> findAllActivities() {
        return iTrainingSessionService.findAllTrainingSession();
    }
    @PostMapping("/addTrainingSession/{id}")
    public ResponseEntity<?> addTrainingSession(@RequestBody @Valid TrainingSession trainingSession, @PathVariable("id") int roomId) {
        try {
            // Get the room and handle it properly
            Room room = roomService.getRoomById((long) roomId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));

            TrainingSession createdSession = iTrainingSessionService.addTrainingSession(trainingSession, roomId);
            return ResponseEntity.ok(createdSession);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to save training session: " + e.getMessage());
        }
    }

    @PutMapping("/UpdateTrainingSession")
    public TrainingSession UpdateTrainingSession(@RequestBody TrainingSession trainingSession) {
        logger.debug("Updating training session: {}", trainingSession);
        return iTrainingSessionService.UpdateTrainingSession(trainingSession);
    }

    @DeleteMapping("/deleteTrainingSessionById/{ts_id}")
    public void deleteTrainingSessionById(@PathVariable("ts_id") Long ts_id) {
        logger.debug("Deleting training session with ID: {}", ts_id);
        iTrainingSessionService.deleteTrainingSessionById(ts_id);
    }

    @GetMapping("/rooms/available")
    public List<Room> getAvailableRooms() {
        return iTrainingSessionService.findAvailableRooms();
    }
}
