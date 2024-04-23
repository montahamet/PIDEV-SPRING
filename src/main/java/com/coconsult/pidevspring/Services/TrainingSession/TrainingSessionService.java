package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.RegistrationTS;
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
    public TrainingSession addTrainingSession(TrainingSession trainingSession, int roomId) {
        logger.info("Adding new training session: {}", trainingSession);
        try {
            // Fetch the corresponding room by roomId (not from within the trainingSession)
            Room room = roomRepository.findById((long)roomId)
                    .orElseThrow(() -> new RuntimeException("Room with ID " + roomId + " not found"));
            trainingSession.setRoom(room); // Setting the correct room based on roomId

            return trainingSessionRepository.save(trainingSession);
        } catch (Exception e) {
            logger.error("Error saving training session: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save the training session: " + e.getMessage(), e);
        }
    }

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
