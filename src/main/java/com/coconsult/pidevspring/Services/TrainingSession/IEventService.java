package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Activity;
import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Entities.RegistrationEvent;
import com.coconsult.pidevspring.DAO.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface IEventService {
    public Event saveEventWithLocation(Event event);
    Page<Event> findAllEvent(Pageable pageable);
    Event addEvent(Event event);
    Event UpdateEvent(Event event);
    public boolean hasRelatedActivities(Long eventId) ;
    void deleteEventById (Long Event_id);
    Event findOneEvent (Long event_id );
    Set<RegistrationEvent> getRelatedRegistrations(Long event_id);
    public Set<User> getRelatedUsers(Long eventId);
    public Set<Activity> getRelatedActivities(Long eventId);
//    public void likeEvent(Long eventId, Long userId);
//    public void dislikeEvent(Long eventId, Long userId) ;

    }
