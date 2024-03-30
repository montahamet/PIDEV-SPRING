package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Activity;
import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Entities.RegistrationEvent;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.EventRepository;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.FeedBackRepository;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.RegistrationEventRepository;
import com.coconsult.pidevspring.DAO.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EventService implements IEventService {
    private final EventRepository eventRepository;
    private  FeedBackRepository feedbackRepository;
    private final RegistrationEventRepository registrationEventRepository;
    private UserRepository userRepository;

    @Autowired
    public EventService(EventRepository eventRepository, RegistrationEventRepository registrationEventRepository) {
        this.eventRepository = eventRepository;
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
