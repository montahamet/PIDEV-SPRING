package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.QuizQuestion;
import com.coconsult.pidevspring.DAO.Repository.HR.QuizQuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuizQuestionService implements IQuizQuestionService{
    private final QuizQuestionRepository quizQuestionRepository;



    // Method to save a quiz question
    public QuizQuestion saveQuizQuestion(QuizQuestion quizQuestion) {
        return quizQuestionRepository.save(quizQuestion);
    }
    public List<QuizQuestion> getAllQuizQuestions() {
        return quizQuestionRepository.findAll();
    }
}
