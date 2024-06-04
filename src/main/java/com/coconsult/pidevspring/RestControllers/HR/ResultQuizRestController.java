package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.DAO.Entities.ResultQuiz;
import com.coconsult.pidevspring.Services.HR.IResultQuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
@RequestMapping( "/resultQuiz")
public class ResultQuizRestController {
    private final IResultQuizService resultQuizService;

    @PostMapping("/add")
    public ResponseEntity<ResultQuiz> addResult(@RequestBody ResultQuiz resultQuiz) {
        ResultQuiz addedResult = resultQuizService.addResult(resultQuiz);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedResult);
    }

}
