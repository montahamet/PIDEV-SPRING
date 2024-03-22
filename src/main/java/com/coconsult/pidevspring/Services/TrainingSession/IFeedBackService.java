package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.FeedBack;

import java.util.List;

public interface IFeedBackService {
    List<FeedBack> findAllFeedBack();
    FeedBack addFeedBack(FeedBack feedBack);
    FeedBack UpdateFeedBack(FeedBack feedBack);

    void deleteFeedBackById (Long feedback_id);
    FeedBack findOneFeedBack (Long feedback_id);
}
