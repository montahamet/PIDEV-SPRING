package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.PerformanceEvaluation;
import com.coconsult.pidevspring.DAO.Repository.HR.PerformanceEvaluationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PerformanceEvaluationService implements IPerformanceEvaluationService{
    PerformanceEvaluationRepository performanceEvaluationRepository;
    @Override
    public PerformanceEvaluation addPerformanceEvaluation(PerformanceEvaluation performanceEvaluation) {
        return performanceEvaluationRepository.save(performanceEvaluation);
    }

    @Override
    public List<PerformanceEvaluation> addAllPerformanceEvaluations(List<PerformanceEvaluation> performanceEvaluations) {
        return performanceEvaluationRepository.saveAll(performanceEvaluations);
    }

    @Override
    public PerformanceEvaluation updatePerformanceEvaluation(PerformanceEvaluation performanceEvaluation) {
        return performanceEvaluationRepository.save(performanceEvaluation);
    }

    @Override
    public List<PerformanceEvaluation> findAllPerformanceEvaluations() {
        return performanceEvaluationRepository.findAll();
    }

    @Override
    public PerformanceEvaluation findPerformanceEvaluationById(long id) {
        return performanceEvaluationRepository.findById(id).get();
    }
    @Override
    public List<PerformanceEvaluation> findByRatingOrderByDesc() {
        return performanceEvaluationRepository.findByRatingOrderByDesc();
    }
}
