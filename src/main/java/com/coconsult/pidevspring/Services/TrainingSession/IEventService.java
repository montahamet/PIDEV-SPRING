package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IEventService {
    public Event saveEventWithLocation(Event event);
    Page<Event> findAllEvent(Pageable pageable);
    public List<Event> findAllEvent2() ;

        Event addEvent(Event event);
    Event UpdateEvent(Event event);
    public boolean hasRelatedActivities(Long eventId) ;
    void deleteEventById (Long Event_id);
    Event findOneEvent (Long event_id );
    Set<RegistrationEvent> getRelatedRegistrations(Long event_id);
    public Set<User> getRelatedUsers(Long eventId);
    public Set<Activity> getRelatedActivities(Long eventId);
    public void updateEventAverageRating(Long eventId);
    public void updateUserStatus(Long eventId, Long userId, Status status) throws Exception ;

        public List<Event> getUpcomingEvents() ;
    public Page<Event> findAllEventsAfterToday(Pageable pageable) ;

    public List<Event> findEventsBetweenDates(LocalDate startDate, LocalDate endDate) ;

    List<User> findUsersByEventId(Long eventId);
    public List<Event> findEventsWithFinishDateGreaterThanSysdate() ;

    String getUserEmailById(Long userId);
}
