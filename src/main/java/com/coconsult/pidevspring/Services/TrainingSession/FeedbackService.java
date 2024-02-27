package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Entities.FeedBack;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.FeedBackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackService implements IFeedBackService{
    FeedBackRepository feedBackRepository;

    @Override
    public List<FeedBack> findAllFeedBack() {
        return feedBackRepository.findAll();
    }

    @Override
    public FeedBack addFeedBack(FeedBack feedBack) {
        return feedBackRepository.save(feedBack);
    }

    @Override
    public FeedBack updateFeedBack(FeedBack feedBack) {
        return feedBackRepository.save(feedBack);
    }

    @Override
    public void deleteFeedBack(FeedBack feedBack) {
        feedBackRepository.delete(feedBack);
    }

    @Override
    public FeedBack findOneFeedBack(Long feedback_id) {
        return feedBackRepository.findById(feedback_id).get();
    }
}
