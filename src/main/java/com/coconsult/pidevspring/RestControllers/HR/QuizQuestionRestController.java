package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.DAO.Entities.QuizQuestion;
import com.coconsult.pidevspring.Services.HR.QuizQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
@RequestMapping( "/quiz")
public class QuizQuestionRestController {
    private final QuizQuestionService quizQuestionService;

    @PostMapping("/question")
    public QuizQuestion createQuizQuestion(@RequestBody QuizQuestion quizQuestion) {
        return quizQuestionService.saveQuizQuestion(quizQuestion);
    }

    @GetMapping("/questions")
    public List<QuizQuestion> getAllQuizQuestions() {
        return quizQuestionService.getAllQuizQuestions();
    }

}
