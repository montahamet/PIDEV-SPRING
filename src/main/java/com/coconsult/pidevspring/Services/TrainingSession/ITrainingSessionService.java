package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Room;
import com.coconsult.pidevspring.DAO.Entities.TrainingSession;

import java.util.List;

public interface ITrainingSessionService {
    List<TrainingSession> findAllTrainingSession();
    TrainingSession UpdateTrainingSession(TrainingSession trainingSession);
//    TrainingSession addTrainingSession(TrainingSession trainingSession, Integer roomId);
//
//    public TrainingSession addTrainingSessionWithRoom(TrainingSession trainingSession, Integer roomId) ;
TrainingSession addTrainingSessionWithRoom(TrainingSession trainingSession, Long roomId);
    TrainingSession addTrainingSessionWithoutRoom(TrainingSession trainingSession);
    void deleteTrainingSessionById (Long TS_id);
    TrainingSession findOneTrainingSession (Long TS_id);
     List<Room> findAvailableRooms() ;
}
