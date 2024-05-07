package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITrainingSessionService {
    Page<TrainingSession> findAllTrainingSession(Pageable pageable);
    TrainingSession UpdateTrainingSession(TrainingSession trainingSession);
//    TrainingSession addTrainingSession(TrainingSession trainingSession, Integer roomId);
//
//    public TrainingSession addTrainingSessionWithRoom(TrainingSession trainingSession, Integer roomId) ;
     TrainingSession addTrainingSessionWithRoom(TrainingSession trainingSession, Long roomId, Long trainerId) ;
    public TrainingSession addTrainingSessionWithoutRoom(TrainingSession trainingSession, Long trainerId) ;
    void deleteTrainingSessionById (Long TS_id);
    TrainingSession findOneTrainingSession (Long TS_id);
     List<Room> findAvailableRooms() ;
     boolean updateTrainingSessionStatus(Long sessionId, TS_Status newStatus) ;
    public RegistrationTS registerUserToSession(Long sessionId, Long userId) ;
    List<User> findUsersBytrainingId(Long sessionId) ;
    public List<User> findUsersByTrainingSessionId(Long sessionId) ;
    }
