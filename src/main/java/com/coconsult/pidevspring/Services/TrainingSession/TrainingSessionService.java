package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.*;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.RegistrationTSRepository;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.RoomRepository;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.TrainingSessionRepository;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import com.coconsult.pidevspring.Services.User.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrainingSessionService implements ITrainingSessionService{
    private final Logger logger = LoggerFactory.getLogger(TrainingSessionService.class);


    @Autowired
    TrainingSessionRepository trainingSessionRepository;
    RoomRepository roomRepository;
    UserRepository userRepository;
    RegistrationTSRepository registrationTSRepository;
    UserService userService;

    @Override
    public Page<TrainingSession> findAllTrainingSession(Pageable pageable) {
        return trainingSessionRepository.findAll(pageable);
    }

    @Override
    public TrainingSession UpdateTrainingSession(TrainingSession trainingSession) {
        return trainingSessionRepository.save(trainingSession);
    }

//    @Override
//    public TrainingSession addTrainingSessionWithRoom(TrainingSession trainingSession, Long roomId, Long trainerId) {
//        // Retrieve the room using the provided ID
//        Room room = roomRepository.findById(roomId)
//                .orElseThrow(() -> new RuntimeException("Room not found"));
//        trainingSession.setRoom(room);
//
//        // Optionally set the trainer if a trainerId is provided
//        if (trainerId != null) {
//            User trainer = userRepository.findById(trainerId)
//                    .orElseThrow(() -> new RuntimeException("Trainer not found"));
//            trainingSession.setTrainer(trainer);
//        }
//
//        // Save the new training session to the database
//        return trainingSessionRepository.save(trainingSession);
//    }
//@Override
//public TrainingSession addTrainingSessionWithRoom(TrainingSession trainingSession, Long roomId, Long trainerId) {
//    Room room = roomRepository.findById(roomId)
//            .orElseThrow(() -> new RuntimeException("Room not found"));
//
//    // Convert LocalDateTime to Date
//    Date startDate = java.sql.Timestamp.valueOf(trainingSession.getStart_date());
//    Date endDate = java.sql.Timestamp.valueOf(trainingSession.getFinish_date());
//
//    // Vérifiez si la salle est disponible pour la période demandée
//    if (!isRoomAvailable(room, startDate, endDate)) {
//        throw new IllegalStateException("Room is not available for the selected dates.");
//    }
//
//    // Ajoutez les nouvelles dates de réservation à la salle
//    room.getBookingDates().add(startDate);
//    room.getBookingDates().add(endDate);
//
//    // Sauvegardez la salle avec les nouvelles dates de réservation
//    roomRepository.save(room);
//
//    trainingSession.setRoom(room);
//
//    if (trainerId != null) {
//        User trainer = userRepository.findById(trainerId)
//                .orElseThrow(() -> new RuntimeException("Trainer not found"));
//        trainingSession.setTrainer(trainer);
//    }
//
//    return trainingSessionRepository.save(trainingSession);
//}
@Override
public TrainingSession addTrainingSessionWithRoom(TrainingSession trainingSession, Long roomId, Long trainerId) {
    Room room = roomRepository.findById(roomId)
            .orElseThrow(() -> new RuntimeException("Room not found"));

    // Convert LocalDateTime to Date
    LocalDateTime startDate = trainingSession.getStart_date();
    LocalDateTime endDate = trainingSession.getFinish_date();

    // Vérifiez si la salle est disponible pour la période demandée
    if (!isRoomAvailable(room.getId(), startDate, endDate)) {
        throw new IllegalStateException("Room is not available for the selected dates.");
    }

    // Ajoutez les nouvelles dates de réservation à la salle
    room.getBookingDates().add(java.sql.Timestamp.valueOf(startDate));
    room.getBookingDates().add(java.sql.Timestamp.valueOf(endDate));

    // Sauvegardez la salle avec les nouvelles dates de réservation
    roomRepository.save(room);

    trainingSession.setRoom(room);

    if (trainerId != null) {
        User trainer = userRepository.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));
        trainingSession.setTrainer(trainer);
    }

    return trainingSessionRepository.save(trainingSession);
}

    private boolean isRoomAvailable(Room room, Date start, Date end) {
        // Implémentez la logique pour vérifier les chevauchements de dates
        return room.getBookingDates().stream().noneMatch(date -> date.compareTo(start) >= 0 && date.compareTo(end) <= 0);
    }
    public boolean isRoomAvailable(Long roomId, LocalDateTime startDate, LocalDateTime endDate) {
        List<LocalDateTime> overlappingDates = roomRepository.findOverlappingDates(roomId, startDate, endDate);
        if (!overlappingDates.isEmpty()) {
            String dates = overlappingDates.stream()
                    .map(date -> date.toString())
                    .collect(Collectors.joining(", "));
            throw new IllegalStateException("Room is already booked for the following dates: " + dates);
        }
        return true;
    }

    @Override
    public TrainingSession addTrainingSessionWithoutRoom(TrainingSession trainingSession, Long trainerId) {
        // Set room to null since this session does not require a room
        trainingSession.setRoom(null);

        // Optionally set the trainer if a trainerId is provided
        if (trainerId != null) {
            User trainer = userService.retrieveOneUser(trainerId);

            trainingSession.setTrainer(trainer);
        }

        // Save the training session to the database
        return trainingSessionRepository.save(trainingSession);
    }

//    public TrainingSession addTrainingSession(TrainingSession trainingSession, Integer roomId) {
//        logger.info("Adding new training session, Type: {}, Room ID: {}", trainingSession.getTypeTS(), roomId);
//        try {
//            if (!trainingSession.getTypeTS().equals("ONLINE")) {
//                if (trainingSession.getTypeTS().equals("INTERNAL")) {
//                    if (roomId == null) {
//                        throw new IllegalArgumentException("Room ID must be provided for internal sessions");
//                    }
//                    Room room = roomRepository.findById((long) roomId)
//                            .orElseThrow(() -> new RuntimeException("Room with ID " + roomId + " not found"));
//                    trainingSession.setRoom(room);
//                } else if (trainingSession.getTypeTS().equals("EXTERNAL")) {
//                    trainingSession.setRoom(null); // No room needed for external
//                }
//            } else {
//                trainingSession.setRoom(null); // No room needed for online sessions
//            }
//            return trainingSessionRepository.save(trainingSession);
//        } catch (Exception e) {
//            logger.error("Error adding training session: {}", e.getMessage(), e);
//            throw new RuntimeException("Failed to add the training session: " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public TrainingSession addTrainingSessionWithRoom(TrainingSession trainingSession, Integer roomId) {
//        logger.info("Adding new training session with room ID: {}", roomId);
//        try {
//            Room room = roomRepository.findById((long) roomId)
//                    .orElseThrow(() -> new RuntimeException("Room with ID " + roomId + " not found"));
//            trainingSession.setRoom(room);
//            return trainingSessionRepository.save(trainingSession);
//        } catch (Exception e) {
//            logger.error("Error adding training session with room: {}", e.getMessage(), e);
//            throw new RuntimeException("Failed to add the training session with room: " + e.getMessage(), e);
//        }
//    }


    @Override
    public void deleteTrainingSessionById(Long ts_id) {
        trainingSessionRepository.deleteById(ts_id);
    }
@Override
    public TrainingSession findOneTrainingSession(Long ts_id) {
        return trainingSessionRepository.findByIdWithRoom(ts_id)
                .orElseThrow(() -> new EntityNotFoundException("Training session not found for id: " + ts_id));
    }



    @Override
    public List<Room> findAvailableRooms() {
        return roomRepository.findByAvailable(true);

    }

    @Transactional
    @Override
    public boolean updateTrainingSessionStatus(Long sessionId, TS_Status newStatus) {
        Optional<TrainingSession> session = trainingSessionRepository.findById(sessionId);
        if (session.isPresent()) {
            TrainingSession updatedSession = session.get();
            updatedSession.setTsStatus(newStatus);
            trainingSessionRepository.save(updatedSession);
            return true;
        }
        return false;
    }

    public RegistrationTS registerUserToSession(Long sessionId, Long userId) {
        TrainingSession session = trainingSessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Session not found with id: " + sessionId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        if (session.getRegistrationtss().size() >= session.getCapacity()) {
            throw new IllegalStateException("Session is full and cannot accommodate more participants.");
        }

        RegistrationTS registration = new RegistrationTS();
        registration.setUser(user);
        registration.setTrainingSession(session);
        registration.setRegistrationTS_date(java.time.LocalDateTime.now());
        registration.setRegistrationTS_status(Status.PENDING);  // Assuming Status is an enum

        return registrationTSRepository.save(registration);
    }

//    public TrainingSession getTrainingSessionWithUsers(Long sessionId) {
//        return trainingSessionRepository.findByIdWithUsers(sessionId)
//                .orElseThrow(() -> new OpenApiResourceNotFoundException("Session not found with ID: " + sessionId));
//    }
@Override

    public List<User> findUsersBytrainingId(Long sessionId) {
        return trainingSessionRepository.findUsersBySessionId(sessionId);
    }
    @Override
    public List<User> findUsersByTrainingSessionId(Long sessionId) {
        return trainingSessionRepository.findUsersBySessionId(sessionId);
    }

}
