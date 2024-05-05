package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Entities.FeedBack;

import java.util.List;

public interface IFeedBackService {
    public void addFeedback(FeedBack feedback) ;
        List<FeedBack> findAllFeedBack();
    public FeedBack addFeedback(Long eventId, FeedBack feedback) ;
    FeedBack UpdateFeedBack(FeedBack feedBack);
    public FeedBack addFeedbackWithNote(Long eventId, String description, int note);
    public List<FeedBack> findAllFeedbacksForEvent(Long eventId);
    public List<Event> getEventsWithAverageRatings();
    public double calculateAverageRatingForEvent(Long eventId) ;
    public Double getAverageRatingForEvent(Long eventId);
    public List<FeedBack> getFeedbacksForEvent(Long eventId);
    void deleteFeedBackById (Long feedback_id);
    FeedBack findOneFeedBack (Long feedback_id);
//    public void analyzeAndSaveFeedback(FeedBack feedback, String language) ;
    public FeedBack addFeedbackWithNoteAndSentiment(Long userId, Long eventId, String description, int note) ;
}
