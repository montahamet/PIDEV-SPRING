package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Room;
import com.coconsult.pidevspring.DAO.Entities.TS_Status;
import com.coconsult.pidevspring.DAO.Entities.TrainingSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITrainingSessionService {
    Page<TrainingSession> findAllTrainingSession(Pageable pageable);
    TrainingSession UpdateTrainingSession(TrainingSession trainingSession);
//    TrainingSession addTrainingSession(TrainingSession trainingSession, Integer roomId);
//
//    public TrainingSession addTrainingSessionWithRoom(TrainingSession trainingSession, Integer roomId) ;
TrainingSession addTrainingSessionWithRoom(TrainingSession trainingSession, Long roomId);
    TrainingSession addTrainingSessionWithoutRoom(TrainingSession trainingSession);
    void deleteTrainingSessionById (Long TS_id);
    TrainingSession findOneTrainingSession (Long TS_id);
     List<Room> findAvailableRooms() ;
     boolean updateTrainingSessionStatus(Long sessionId, TS_Status newStatus) ;

}
