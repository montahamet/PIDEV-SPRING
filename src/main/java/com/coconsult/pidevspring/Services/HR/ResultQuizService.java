package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.ResultQuiz;
import com.coconsult.pidevspring.DAO.Repository.HR.ResultQuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResultQuizService implements IResultQuizService{
    private final ResultQuizRepository resultQuizRepository;

    @Override
    public ResultQuiz addResult(ResultQuiz resultQuiz) {
        // You can add any validation logic here before saving the result
        return resultQuizRepository.save(resultQuiz);
    }
}
