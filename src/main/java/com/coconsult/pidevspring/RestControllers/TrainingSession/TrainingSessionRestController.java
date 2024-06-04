package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.*;
import com.coconsult.pidevspring.Services.TrainingSession.IRoomService;
import com.coconsult.pidevspring.Services.TrainingSession.ITrainingSessionService;
import com.coconsult.pidevspring.Services.TrainingSession.RoomService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    ITrainingSessionService iTrainingSessionService;
    IRoomService roomService;
    private static final Logger logger = LoggerFactory.getLogger(TrainingSessionRestController.class); // Logger added here

    @GetMapping("/findOneTrainingSession/{ts_id}")
    public ResponseEntity<TrainingSession> findOneTrainingSession(@PathVariable Long ts_id) {
        TrainingSession dto = iTrainingSessionService.findOneTrainingSession(ts_id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findAllTrainingSession")
    public Page<TrainingSession> findAllActivities(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return iTrainingSessionService.findAllTrainingSession(pageable);
    }
//    @PostMapping("/addTrainingSession/{id}")
//    public ResponseEntity<?> addTrainingSession(@RequestBody @Valid TrainingSession trainingSession, @PathVariable("id") int roomId) {
//        try {
//            // Get the room and handle it properly
//            Room room = roomService.getRoomById((long) roomId)
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));
//
//            TrainingSession createdSession = iTrainingSessionService.addTrainingSession(trainingSession, roomId);
//            return ResponseEntity.ok(createdSession);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Failed to save training session: " + e.getMessage());
//        }
//    }
//    @PostMapping({"/addTrainingSession", "/addTrainingSession/{id}"})
//    public ResponseEntity<?> addTrainingSession(@RequestBody @Valid TrainingSession trainingSession,
//                                                @PathVariable(required = false) Integer roomId) {
//        try {
//            if (roomId != null) {
//                // Si roomId est fourni, vérifiez l'existence de la salle
//                Room room = roomService.getRoomById((long) roomId)
//                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));
//
//                // Ajoutez la session de formation avec le roomId spécifié
//                TrainingSession createdSession = iTrainingSessionService.addTrainingSession(trainingSession, roomId);
//                return ResponseEntity.ok(createdSession);
//            } else {
//                // Si aucun roomId n'est fourni, ajoutez la session de formation sans spécifier de salle
//                TrainingSession createdSession = iTrainingSessionService.addTrainingSession(trainingSession, null);
//                return ResponseEntity.ok(createdSession);
//            }
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Failed to save training session: " + e.getMessage());
//        }
//    }


    // ce que je veux faire est que il y'a deux methode pour ajouter une formation l'une qui est en ligne donc il peut ne pas ajouter une room mais si ce n'est pas en ligne donc il vas choisir si c'est internal il vas devoir choisir une salle mais si external il va pas choisir une salle
//@PostMapping("/addTrainingSession")
//public ResponseEntity<?> addTrainingSession(@RequestParam(required = false) Integer roomId, @RequestBody @Valid TrainingSession trainingSession) {
//    try {
//        TrainingSession createdSession = iTrainingSessionService.addTrainingSession(trainingSession, roomId);
//        return ResponseEntity.ok(createdSession);
//    } catch (Exception e) {
//        return ResponseEntity.badRequest().body("Failed to save training session: " + e.getMessage());
//    }
//}
//
//
//
//    // Endpoint pour ajouter une session de formation avec roomId
//    @PostMapping("/addTrainingSession/{roomId}")
//    public ResponseEntity<?> addTrainingSessionWithRoom(
//            @PathVariable("roomId") int roomId,
//            @RequestBody @Valid TrainingSession trainingSession) {
//        try {
//            Room room = roomService.getRoomById((long) roomId)
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));
//            TrainingSession createdSession = iTrainingSessionService.addTrainingSession(trainingSession, roomId);
//            return ResponseEntity.ok(createdSession);
//        } catch (ResponseStatusException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Failed to save training session: " + e.getMessage());
//        }
//    }
//    @PostMapping("/with-room/{roomId}/{trainerId}")
//    public ResponseEntity<TrainingSession> addTrainingSessionWithRoom(
//            @RequestBody TrainingSession trainingSession,
//            @PathVariable Long roomId,
//            @PathVariable Long trainerId) {
//
//        TrainingSession createdSession = iTrainingSessionService.addTrainingSessionWithRoom(trainingSession, roomId, trainerId);
//        return ResponseEntity.ok(createdSession);
//    }
    @PostMapping("/with-room/{roomId}/{trainerId}")
    public ResponseEntity<?> addTrainingSessionWithRoom(
            @RequestBody TrainingSession trainingSession,
            @PathVariable Long roomId,
            @PathVariable Long trainerId) {
        try {
            TrainingSession createdSession = iTrainingSessionService.addTrainingSessionWithRoom(trainingSession, roomId, trainerId);
            return ResponseEntity.ok(createdSession);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/without-room/{trainer}")
    public ResponseEntity<?> addTrainingSessionWithoutRoom(@RequestBody TrainingSession trainingSession, @PathVariable("trainer") Long trainer) {
        logger.info("Received training session: {}", trainingSession);
        logger.info("Trainer ID: {}", trainer);
        try {
            TrainingSession createdSession = iTrainingSessionService.addTrainingSessionWithoutRoom(trainingSession, trainer);
            return ResponseEntity.ok(createdSession);
        } catch (Exception e) {
            logger.error("Error adding training session without room: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Failed to add training session without room: " + e.toString());
        }
    }




    @PutMapping("/UpdateTrainingSession/{ts_id}")
    public ResponseEntity<?> updateTrainingSession(@RequestBody @Valid TrainingSession updatedTrainingSession, @PathVariable Long ts_id) {
        try {
            TrainingSession existingSession = iTrainingSessionService.findOneTrainingSession(ts_id);

            if (existingSession == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Training session not found with id: " + ts_id);
            }

            // Update the existing fields with those from updatedTrainingSession
            existingSession.setTitle(updatedTrainingSession.getTitle());
            existingSession.setStart_date(updatedTrainingSession.getStart_date());
            existingSession.setFinish_date(updatedTrainingSession.getFinish_date());
            existingSession.setTopic(updatedTrainingSession.getTopic());
            existingSession.setCapacity(updatedTrainingSession.getCapacity());
            existingSession.setPlace(updatedTrainingSession.getPlace());
            existingSession.setTypeTS(updatedTrainingSession.getTypeTS());
            existingSession.setTsStatus(updatedTrainingSession.getTsStatus());
            existingSession.setRoom(updatedTrainingSession.getRoom()); // Make sure room handling is correct

            TrainingSession savedSession = iTrainingSessionService.UpdateTrainingSession(existingSession);
            return ResponseEntity.ok(savedSession);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to update training session", e);
            return ResponseEntity.badRequest().body("Failed to update training session: " + e.getMessage());
        }
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

//    @PatchMapping("/{id}/status")
//    public ResponseEntity<?> updateTrainingSessionStatus(@PathVariable Long id, @RequestBody TS_Status status) {
//        boolean updated = iTrainingSessionService.updateTrainingSessionStatus(id, status);
//        if (updated) {
//            return ResponseEntity.ok().body("Status updated successfully");
//        }
//        return ResponseEntity.badRequest().body("Failed to update status");
//    }
@PatchMapping("/{id}/status")
public ResponseEntity<?> updateTrainingSessionStatus(@PathVariable Long id, @RequestBody String statusString) {
    TS_Status status = TS_Status.valueOf(statusString);  // Convert string to enum
    boolean updated = iTrainingSessionService.updateTrainingSessionStatus(id, status);
    if (updated) {
        return ResponseEntity.ok().body("{\"message\": \"Status updated successfully\"}");
    }
    return ResponseEntity.badRequest().body("Failed to update status");
}

    @PostMapping("/{sessionId}/register/{userId}")
    public ResponseEntity<RegistrationTS> registerUserToSession(
            @PathVariable Long sessionId,
            @PathVariable Long userId) {
        try {
            RegistrationTS registration = iTrainingSessionService.registerUserToSession(sessionId, userId);
            return ResponseEntity.ok(registration);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);  // Provide more specific error handling/message as needed
        }
    }
    @GetMapping("/{sessionId}")
    public ResponseEntity<List<User>> getTrainingSessionById(@PathVariable Long sessionId) {
        List<User> users = iTrainingSessionService.findUsersBytrainingId(sessionId);
        if (users == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{sessionId}/users")
    public ResponseEntity<List<User>> getUsersByTrainingSession(@PathVariable Long sessionId) {
        try {
            List<User> users = iTrainingSessionService.findUsersByTrainingSessionId(sessionId);
            if (users.isEmpty()) {
                return ResponseEntity.noContent().build(); // or notFound().build() if you prefer
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error("Error retrieving users for training session", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



}
