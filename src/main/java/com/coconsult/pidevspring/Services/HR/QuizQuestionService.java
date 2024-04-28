package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.QuizQuestion;
import com.coconsult.pidevspring.DAO.Repository.HR.QuizQuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuizQuestionService implements IQuizQuestionService {

    private final QuizQuestionRepository quizQuestionRepository;

    @Override
    public QuizQuestion addQuizQuestion(QuizQuestion quizQuestion) {
        return quizQuestionRepository.save(quizQuestion);
    }

    @Override
    public List<QuizQuestion> getAllQuizQuestions() {
        return quizQuestionRepository.findAll();
    }
    @Override
    public QuizQuestion deleteQuizQuestion(Long questionId) {
        QuizQuestion quizQuestion = quizQuestionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("QuizQuestion not found with id " + questionId));
        quizQuestionRepository.delete(quizQuestion);
        return quizQuestion;
    }
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
    public QuizQuestion editQuizQuestion(Long questionId, QuizQuestion updatedQuizQuestion) {
        QuizQuestion quizQuestion = quizQuestionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("QuizQuestion not found with id " + questionId));
        // Update the quiz question with the new data
        quizQuestion.setQuestion(updatedQuizQuestion.getQuestion());
        quizQuestion.setOptions(updatedQuizQuestion.getOptions());
        // Add any other fields you want to update here
        return quizQuestionRepository.save(quizQuestion);
    }
}
