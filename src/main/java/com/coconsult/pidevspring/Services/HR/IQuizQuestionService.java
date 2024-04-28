package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.QuizQuestion;

import java.util.List;

public interface IQuizQuestionService {
    QuizQuestion addQuizQuestion(QuizQuestion quizQuestion);
     List<QuizQuestion> getAllQuizQuestions();

    QuizQuestion deleteQuizQuestion(Long questionId);
     QuizQuestion editQuizQuestion(Long questionId, QuizQuestion updatedQuizQuestion);

    }
