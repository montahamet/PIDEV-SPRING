package com.coconsult.pidevspring.DAO.Repository.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession,Long> {
    @Query("SELECT ts FROM TrainingSession ts JOIN FETCH ts.room WHERE ts.ts_id = :ts_id")
    Optional<TrainingSession> findByIdWithRoom(Long ts_id);

}
