package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.DAO.Entities.QuizQuestion;
import com.coconsult.pidevspring.Services.HR.QuizQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
}
