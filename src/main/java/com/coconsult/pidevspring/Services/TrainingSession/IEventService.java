package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Entities.Event;

import java.util.List;

public interface IEventService {
    List<Event> findAllEvent();
    Event addEvent(Event event);
    Event updateEvent(Event event);
    void deleteEvent (Event event);
    Event findOneEvent (Long event_id );
}
