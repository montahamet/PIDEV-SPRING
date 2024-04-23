package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.RegistrationTS;
import com.coconsult.pidevspring.DAO.Entities.Room;
import com.coconsult.pidevspring.DAO.Entities.TrainingSession;

import java.util.List;

public interface ITrainingSessionService {
    List<TrainingSession> findAllTrainingSession();
    TrainingSession UpdateTrainingSession(TrainingSession trainingSession);
    TrainingSession addTrainingSession(TrainingSession trainingSession, int roomId);


    void deleteTrainingSessionById (Long TS_id);
    TrainingSession findOneTrainingSession (Long TS_id);
     List<Room> findAvailableRooms() ;
}
