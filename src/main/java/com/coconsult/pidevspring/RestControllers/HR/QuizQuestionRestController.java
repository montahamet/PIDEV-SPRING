package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.DAO.Entities.QuizQuestion;
import com.coconsult.pidevspring.Services.HR.QuizQuestionService;
import lombok.AllArgsConstructor;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
@RequestMapping( "/quiz")
public class QuizQuestionRestController {
    @Autowired
    private QuizQuestionService quizQuestionService;

    @PostMapping("/add")
    public QuizQuestion addQuizQuestion(@RequestBody QuizQuestion quizQuestion) {
        return quizQuestionService.addQuizQuestion(quizQuestion);
    }

    @GetMapping("/questions")
    public List<QuizQuestion> getAllQuizQuestions() {
        return quizQuestionService.getAllQuizQuestions();
    }
    @DeleteMapping("/delete/{questionId}")
    public void deleteQuizQuestion(@PathVariable Long questionId) {
        quizQuestionService.deleteQuizQuestion(questionId);
    }
    @PutMapping("/edit/{questionId}")
    public QuizQuestion editQuizQuestion(@PathVariable Long questionId, @RequestBody QuizQuestion updatedQuizQuestion) {
        return quizQuestionService.editQuizQuestion(questionId, updatedQuizQuestion);
    }
//    @Autowired
//    private  JobLauncher jobLauncher;
//    @Autowired
//    @Qualifier("csvJob")
//    private  Job job;
//    @GetMapping(value = "/startBatch")
//    public ResponseEntity<String> startBatch() {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
//
//        JobExecution run = null;
//        try {
//            run = jobLauncher.run(job, jobParameters);
//        } catch (JobExecutionAlreadyRunningException e) {
//            throw new RuntimeException(e);
//        } catch (JobRestartException e) {
//            throw new RuntimeException(e);
//        } catch (JobInstanceAlreadyCompleteException e) {
//            throw new RuntimeException(e);
//        } catch (JobParametersInvalidException e) {
//            throw new RuntimeException(e);
//        }
//        return ResponseEntity.ok(run.getStatus().toString());
//
//    }
}
