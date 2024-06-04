package com.coconsult.pidevspring.DAO.Repository.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.TrainingSession;
import com.coconsult.pidevspring.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession,Long> {
    @Query("SELECT ts FROM TrainingSession ts JOIN FETCH ts.room WHERE ts.ts_id = :ts_id")
    Optional<TrainingSession> findByIdWithRoom(Long ts_id);
    @Query("SELECT ts FROM TrainingSession ts JOIN FETCH ts.user WHERE ts.ts_id = :id")
    Optional<TrainingSession> findByIdWithUsers1(@Param("id") Long id);

    @Query("SELECT re.user FROM RegistrationEvent re WHERE re.user.userId = :ts_id")
    List<User> findByIdWithUsers(@Param("ts_id") Long tsId);
    @Query("SELECT r.user FROM RegistrationTS r WHERE r.trainingSession.ts_id = :sessionId")
    List<User> findUsersBySessionId(@Param("sessionId") Long sessionId);
@Query("SELECT ts FROM TrainingSession ts WHERE ts.user.userId = :userId AND " +
        "(ts.start_date >= :endDate or ts.finish_date <= :startDate)")
List<TrainingSession> findTrainingSessionsByUserAndDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate);
}
