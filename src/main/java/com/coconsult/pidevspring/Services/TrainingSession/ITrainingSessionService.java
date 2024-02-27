package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.RegistrationTS;
import com.coconsult.pidevspring.DAO.Entities.TrainingSession;

import java.util.List;

public interface ITrainingSessionService {
    List<TrainingSession> findAllTrainingSession();
    TrainingSession addTrainingSession(TrainingSession trainingSession);
    TrainingSession updateTrainingSession(TrainingSession trainingSession);
    void deleteTrainingSession (TrainingSession trainingSession);
    TrainingSession findOneTrainingSession (Long TS_id);
}
