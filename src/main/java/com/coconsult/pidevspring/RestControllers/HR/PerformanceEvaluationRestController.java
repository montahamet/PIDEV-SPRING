package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.DAO.Entities.PerformanceEvaluation;
import com.coconsult.pidevspring.Services.HR.IPerformanceEvaluationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/PerformanceEvaluation")
public class PerformanceEvaluationRestController {
    IPerformanceEvaluationService iPerformanceEvaluationService;

    @PostMapping("addPerformanceEvaluation")
    public PerformanceEvaluation addPerformanceEvaluation (@RequestBody PerformanceEvaluation PerformanceEvaluation){
        return iPerformanceEvaluationService.addPerformanceEvaluation(PerformanceEvaluation);
    }


    @PutMapping("updatePerformanceEvaluation")
    public PerformanceEvaluation updatePerformanceEvaluation (@RequestBody  PerformanceEvaluation PerformanceEvaluation){
        return iPerformanceEvaluationService.updatePerformanceEvaluation(PerformanceEvaluation);
    }

    @PostMapping("addAllPerformanceEvaluations")
    public List<PerformanceEvaluation> addAllPerformanceEvaluations(@RequestBody List<PerformanceEvaluation> PerformanceEvaluations){
        return iPerformanceEvaluationService.addAllPerformanceEvaluations(PerformanceEvaluations);
    }

    @GetMapping("getPerformanceEvaluation/{id}")
    public PerformanceEvaluation getPerformanceEvaluation(@PathVariable("id") long id){
        return iPerformanceEvaluationService.findPerformanceEvaluationById(id);
    }
    @GetMapping("findAllPerformanceEvaluations")
    public List<PerformanceEvaluation> findAllPerformanceEvaluations() {
        return iPerformanceEvaluationService.findAllPerformanceEvaluations();
    }
    @GetMapping("/findByRatingOrderByDesc")
    public List<PerformanceEvaluation> findByRatingOrderByDesc() {
        return iPerformanceEvaluationService.findByRatingOrderByDesc();
    }
}
