package com.coconsult.pidevspring.DAO.Repository.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.RegistrationTS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegistrationTSRepository extends JpaRepository<RegistrationTS,Long> {
    @Query("SELECT COUNT(r) > 0 FROM RegistrationTS r WHERE r.trainingSession.id = :tsId AND r.user.id = :userId")
    boolean existsByTrainingSessionIdAndUserId(Long tsId, Long userId);

}
