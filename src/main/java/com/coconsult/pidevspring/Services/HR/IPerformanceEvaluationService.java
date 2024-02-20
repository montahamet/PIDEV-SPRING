package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.PerformanceEvaluation;

import java.util.List;

public interface IPerformanceEvaluationService {
    PerformanceEvaluation addPerformanceEvaluation(PerformanceEvaluation performanceEvaluation);
    List<PerformanceEvaluation> addAllPerformanceEvaluations(List<PerformanceEvaluation> performanceEvaluations);
    PerformanceEvaluation updatePerformanceEvaluation(PerformanceEvaluation performanceEvaluation);
    List<PerformanceEvaluation> findAllPerformanceEvaluations();
    PerformanceEvaluation findPerformanceEvaluationById(long id);
}
