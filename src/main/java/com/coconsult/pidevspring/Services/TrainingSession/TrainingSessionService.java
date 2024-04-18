package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.RegistrationTS;
import com.coconsult.pidevspring.DAO.Entities.TrainingSession;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.TrainingSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrainingSessionService implements ITrainingSessionService{
<<<<<<< HEAD
=======
    @Autowired

>>>>>>> 340bb1611de4d28d73c923a57941f8b1cd8d1183
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
