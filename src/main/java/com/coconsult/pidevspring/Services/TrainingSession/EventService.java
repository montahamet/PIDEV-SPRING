package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Activity;
import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Entities.RegistrationEvent;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.EventRepository;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.RegistrationEventRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EventService implements IEventService {
    private final EventRepository eventRepository;
    private final RegistrationEventRepository registrationEventRepository;

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
    public List<Event> findAllEvent() {
        return eventRepository.findAll();
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
    // Vérifier si l'événement existe avant de le mettre à jour
    return eventRepository.findById(event.getEvent_id())
            .map(existingEvent -> {
                // Mettez à jour ici les champs de l'existingEvent avec ceux de l'event
                existingEvent.setEvent_name(event.getEvent_name());
                existingEvent.setEvent_description(event.getEvent_description());
                existingEvent.setEvent_date(event.getEvent_date());
                existingEvent.setPlace(event.getPlace());
                // Ajoutez d'autres champs à mettre à jour si nécessaire
                return eventRepository.save(existingEvent);
            })
            .orElseThrow(() -> new OpenApiResourceNotFoundException("Event not found with id " + event.getEvent_id()));
}


    @Override
    public void deleteEventById(Long eventId) {
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
}
