package com.coconsult.pidevspring.Services.HR.Config;

import com.coconsult.pidevspring.DAO.Entities.QuizQuestion;
import org.springframework.batch.item.ItemProcessor;

public class QuizQuestionProcessor implements ItemProcessor<QuizQuestion, QuizQuestion> {
    @Override
    public QuizQuestion process(QuizQuestion item) throws Exception {
        return item;
    }
}
