package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.RegistrationTS;
import com.coconsult.pidevspring.DAO.Entities.TrainingSession;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.TrainingSessionRepository;
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
    @Override
    public List<TrainingSession> findAllTrainingSession() {
        return trainingSessionRepository.findAll();
    }

    @Override
    public TrainingSession UpdateTrainingSession(TrainingSession trainingSession) {
        return trainingSessionRepository.save(trainingSession);
    }

    @Override
    public TrainingSession addTrainingSession(TrainingSession trainingSession) {
        logger.info("Adding new training session: {}", trainingSession);
        try {
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
        return trainingSessionRepository.findById(ts_id).get();
    }
}
