package com.coconsult.pidevspring.DAO.Repository.HR;

import com.coconsult.pidevspring.DAO.Entities.PerformanceEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerformanceEvaluationRepository extends JpaRepository<PerformanceEvaluation,Long> {
    @Query("SELECT pe FROM PerformanceEvaluation pe ORDER BY pe.rating DESC")
    List<PerformanceEvaluation> findByRatingOrderByDesc();
}
