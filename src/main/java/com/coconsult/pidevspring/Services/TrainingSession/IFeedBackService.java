package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.FeedBack;

import java.util.List;

public interface IFeedBackService {
    List<FeedBack> findAllFeedBack();
    FeedBack addFeedBack(FeedBack feedBack);
    FeedBack updateFeedBack(FeedBack feedBack);
    void deleteFeedBack (FeedBack feedBack);
    FeedBack findOneFeedBack (Long feedback_id);
}
