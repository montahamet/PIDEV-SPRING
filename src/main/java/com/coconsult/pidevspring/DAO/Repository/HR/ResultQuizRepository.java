package com.coconsult.pidevspring.DAO.Repository.HR;

import com.coconsult.pidevspring.DAO.Entities.QuizQuestion;
import com.coconsult.pidevspring.DAO.Entities.ResultQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultQuizRepository extends JpaRepository<ResultQuiz, Long> {
}
