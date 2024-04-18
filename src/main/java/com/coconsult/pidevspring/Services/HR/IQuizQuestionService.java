package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.QuizQuestion;

import java.util.List;

public interface IQuizQuestionService {
     QuizQuestion saveQuizQuestion(QuizQuestion quizQuestion);
     List<QuizQuestion> getAllQuizQuestions();


    }
