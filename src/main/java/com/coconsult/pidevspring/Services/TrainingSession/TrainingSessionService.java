package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Room;
import com.coconsult.pidevspring.DAO.Entities.TrainingSession;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.RoomRepository;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.TrainingSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrainingSessionService implements ITrainingSessionService{
    private final Logger logger = LoggerFactory.getLogger(TrainingSessionService.class);


    @Autowired
    TrainingSessionRepository trainingSessionRepository;
    RoomRepository roomRepository;
    @Override
    public List<TrainingSession> findAllTrainingSession() {
        return trainingSessionRepository.findAll();
    }

    @Override
    public TrainingSession UpdateTrainingSession(TrainingSession trainingSession) {
        return trainingSessionRepository.save(trainingSession);
    }

    @Override
    public TrainingSession addTrainingSessionWithRoom(TrainingSession trainingSession, Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        trainingSession.setRoom(room);
        return trainingSessionRepository.save(trainingSession);
    }

    @Override
    public TrainingSession addTrainingSessionWithoutRoom(TrainingSession trainingSession) {
        trainingSession.setRoom(null);
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
}
