package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.*;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.EventRepository;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.FeedBackRepository;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.RegistrationEventRepository;

import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EventService implements IEventService {
    @Autowired
    private final EventRepository eventRepository;
    private final FeedBackRepository feedbackRepository;
    private final RegistrationEventRepository registrationEventRepository;

    @Autowired
    public EventService(EventRepository eventRepository, FeedBackRepository feedbackRepository, RegistrationEventRepository registrationEventRepository) {
        this.eventRepository = eventRepository;
        this.feedbackRepository = feedbackRepository;
        this.registrationEventRepository = registrationEventRepository;
    }
    @Override
    public boolean hasRelatedActivities(Long eventId) {
        return eventRepository.existsByEventId(eventId);
    }

    @Override
    public Event saveEventWithLocation(Event event) {
        return eventRepository.save(event);
    }
    @Override
    public Page<Event> findAllEventsAfterToday(Pageable pageable) {
        LocalDate today = LocalDate.now();
        return eventRepository.findAllWithDateAfter(today, pageable);
    }
    @Override
    public Page<Event> findAllEvent(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    @Override
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

//    @Override
//    public Event UpdateEvent(Event event) {
//        return eventRepository.save(event);
//    }
@Override
public Event UpdateEvent(Event event) {
    return eventRepository.findById(event.getEventId())
            .map(existingEvent -> {
                existingEvent.setEvent_name(event.getEvent_name());
                existingEvent.setEvent_description(event.getEvent_description());
                existingEvent.setEvent_date(event.getEvent_date());
                existingEvent.setPlace(event.getPlace());
                return eventRepository.save(existingEvent);
            })
            .orElseThrow(() -> new OpenApiResourceNotFoundException("Event not found with id " + event.getEventId()));
}


    @Override

    public void deleteEventById(Long eventId) {
        feedbackRepository.deleteByEventId(eventId);
        registrationEventRepository.deleteByEventId(eventId);
        eventRepository.deleteById(eventId);
    }


    @Override
    public Event findOneEvent(Long eventId) {
        return eventRepository.findById(eventId).get();
    }

    @Override
    public Set<RegistrationEvent> getRelatedRegistrations(Long eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            return event.getRegistationEvents();
        }
        return Collections.emptySet();
    }

    @Override
    public Set<User> getRelatedUsers(Long eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        return eventOptional.map(Event::getUsers).orElse(Collections.emptySet());
    }

    @Override
    public Set<Activity> getRelatedActivities(Long eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        return eventOptional.map(Event::getActivitys).orElse(Collections.emptySet());
    }

    @Override
    public void updateEventAverageRating(Long eventId) {
        // Trouver tous les feedbacks pour l'événement
        List<FeedBack> feedbacks = feedbackRepository.findAllByEventEventId(eventId);

        // Calculer la moyenne des notes
        double averageRating = feedbacks.stream()
                .mapToInt(FeedBack::getNote)
                .average()
                .orElse(0.0);

        // Mettre à jour l'événement avec la nouvelle moyenne
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event != null) {
            event.setAverageRating(averageRating);
            eventRepository.save(event);
        }
    }
    @Override
    public List<Event> getUpcomingEvents() {
        LocalDate today = LocalDate.now();
        return eventRepository.findUpcomingEvents(today);
    }

//    @Override
//    public void likeEvent(Long eventId, Long userId) {
//        Event event = eventRepository.findById(eventId)
//                .orElseThrow(() -> new EntityNotFoundException("Event not found"));
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//
//        if (!event.getLikedByUsers().contains(user)) {
//            event.getLikedByUsers().add(user);
//            eventRepository.save(event);
//        } else {
//            // Handle case where user already liked the event
//        }
//    }
//
//    @Override
//    public void dislikeEvent(Long eventId, Long userId) {
//        Event event = eventRepository.findById(eventId)
//                .orElseThrow(() -> new EntityNotFoundException("Event not found"));
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//
//        if (event.getLikedByUsers().contains(user)) {
//            event.getLikedByUsers().remove(user);
//            eventRepository.save(event);
//        } else {
//            // Handle case where user already disliked the event
//        }
//    }

}
