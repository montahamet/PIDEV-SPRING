package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Entities.FeedBack;
//import com.coconsult.pidevspring.DAO.Entities.SentimentAnalyzer;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.EventRepository;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.FeedBackRepository;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class FeedbackService implements IFeedBackService{

    @Autowired

    FeedBackRepository feedBackRepository;
EventRepository eventRepository;
UserRepository userRepository;
    @Override
    public List<FeedBack> findAllFeedBack() {
        return feedBackRepository.findAll();
    }
    public void addFeedback(FeedBack feedback) {
        feedBackRepository.save(feedback);
    }
    @Override
    public FeedBack addFeedback(Long eventId, FeedBack feedback) {
        // Fetch the event
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));

        // Associate the event with the feedback
        feedback.setEvent(event);

        // Save the feedback
        return feedBackRepository.save(feedback);
    }
    @Override
    public FeedBack addFeedbackWithNote(Long eventId, String description, int note) {
        FeedBack feedback = new FeedBack();
        feedback.setDescription(description);
        feedback.setNote(note);

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        feedback.setEvent(event);

        return feedBackRepository.save(feedback);
    }
    @Override
    public FeedBack UpdateFeedBack(FeedBack feedBack) {
        return feedBackRepository.save(feedBack);
    }

    @Override
    public List<FeedBack> findAllFeedbacksForEvent(Long eventId) {
        return feedBackRepository.findAllByEventEventId(eventId);
    }
    @Override
    public List<Event> getEventsWithAverageRatings() {
        List<Event> events = eventRepository.findAll();
        events.forEach(event -> {
            Double averageRating = this.getAverageRatingForEvent(event.getEventId());
            event.setAverageRating(averageRating);
        });
        return events;
    }

    public List<FeedBack> getFeedbacksForEvent(Long eventId) {
        return feedBackRepository.findAllByEventEventId(eventId);
    }


    public Double getAverageRatingForEvent(Long eventId) {
        Double averageRating = feedBackRepository.findAverageRatingByEventId(eventId);
        return averageRating != null ? averageRating : Double.NaN;
    }
    public double calculateAverageRatingForEvent(Long eventId) {
        List<FeedBack> feedbacks = feedBackRepository.findAllByEventEventId(eventId);
        return feedbacks.stream()
                .mapToInt(FeedBack::getNote)
                .average()
                .orElse(Double.NaN); // Retourne NaN si aucune note n'est disponible
    }
//    @Override
//    public void analyzeAndSaveFeedback(FeedBack feedback, String language) {
//        String sentiment = SentimentAnalyzer.findSentiment(feedback.getComment(), language);
//        feedback.setSentiment(sentiment);
//        feedBackRepository.save(feedback);
//    }

    @Override
    public void deleteFeedBackById(Long feedback_id) {
        feedBackRepository.deleteById(feedback_id);
    }

    @Override
    public FeedBack findOneFeedBack(Long feedback_id) {
        return feedBackRepository.findById(feedback_id).get();
    }

//    @Override
//    public void analyzeAndSaveFeedback(FeedBack feedback, String language) {
//        String sentiment = SentimentAnalyzer.findSentiment(feedback.getDescription(), language);
//        feedback.setSentiment(sentiment);
//        feedBackRepository.save(feedback);
//    }

    public FeedBack addFeedbackWithNoteAndSentiment(Long userId, Long eventId, String description, int note) {
        // Find the user associated with the feedback, throw exception if not found
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find the event associated with the feedback, throw exception if not found
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // Analyze the sentiment of the feedback description
//        String sentiment = SentimentAnalyzer.findSentiment(description, "english");  // Assuming the primary language of feedback is English

        // Create and populate the feedback entity
        FeedBack feedback = new FeedBack();
        feedback.setUser(user); // Set the user who is giving the feedback
        feedback.setEvent(event); // Set the event for which the feedback is intended
        feedback.setDescription(description); // Set the description of the feedback
        feedback.setNote(note); // Set the numeric note or rating
//        feedback.setSentiment(sentiment); // Set the analyzed sentiment

        // Save and return the feedback entity
        return feedBackRepository.save(feedback);
    }

    }
