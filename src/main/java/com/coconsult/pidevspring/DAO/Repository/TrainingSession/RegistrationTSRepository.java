package com.coconsult.pidevspring.DAO.Repository.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.RegistrationTS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegistrationTSRepository extends JpaRepository<RegistrationTS,Long> {
    @Query("SELECT COUNT(r) > 0 FROM RegistrationTS r WHERE r.trainingSession.ts_id = :tsId AND r.user.userId = :userId")
    boolean existsByTrainingSessionIdAndUserId(Long tsId, Long userId);
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM RegistrationTS r WHERE r.trainingSession.ts_id = :tsId AND r.user.userId = :userId")
    boolean existsByTrainingSessionIdAndUserId11(@Param("tsId") Long trainingSessionId, @Param("userId") Long userId);
}
