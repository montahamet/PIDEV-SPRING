package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Entities.FeedBack;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.EventRepository;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.FeedBackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class FeedbackService implements IFeedBackService{
    FeedBackRepository feedBackRepository;
EventRepository eventRepository;
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


    @Override
    public void deleteFeedBackById(Long feedback_id) {
        feedBackRepository.deleteById(feedback_id);
    }

    @Override
    public FeedBack findOneFeedBack(Long feedback_id) {
        return feedBackRepository.findById(feedback_id).get();
    }
}
