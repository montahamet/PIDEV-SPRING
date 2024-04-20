package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.RegistrationTS;
import com.coconsult.pidevspring.DAO.Entities.TrainingSession;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.TrainingSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrainingSessionService implements ITrainingSessionService{
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
        return trainingSessionRepository.save(trainingSession);
    }


    @Override
    public void deleteTrainingSessionById(Long TS_id) {
        trainingSessionRepository.deleteById(TS_id);
    }

    @Override
    public TrainingSession findOneTrainingSession(Long TS_id) {
        return trainingSessionRepository.findById(TS_id).get();
    }
}
