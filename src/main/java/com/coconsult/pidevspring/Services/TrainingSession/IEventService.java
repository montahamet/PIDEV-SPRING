package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Entities.Event;

import java.util.List;

public interface IEventService {
    List<Event> findAllEvent();
    Event addEvent(Event event);
    Event updateEvent(Event event);
    void deleteEventById (Long Event_id);
    Event findOneEvent (Long event_id );
}
