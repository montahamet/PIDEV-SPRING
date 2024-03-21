package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Activity;
import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.ActivityRepository;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActivityService implements IActivityService {
    ActivityRepository activityRepository;
    EventRepository eventRepository;
    private static final Logger logger = LoggerFactory.getLogger(ActivityService.class);


    @Override
    public List<Activity> findAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public Activity addActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Activity UpdateActivity(Activity activity, Long eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        Event event = eventOptional.orElseThrow(() -> new EntityNotFoundException("Event with ID " + eventId + " not found."));
        activity.setEvent(event);
        // Logique de mise à jour de l'activité ici
        return activityRepository.save(activity);
    }


    


    @Override
    public void deleteActivityById(Long activity_id) {
        activityRepository.deleteById(activity_id);
    }

    @Override
    public List<Event> getAllEventsWithName() {
        List<Event> events = eventRepository.findAll();
        for (Event event : events) {
            event.setEvent_name(event.getEvent_name());
        }
        return events;
    }


    @Override
    public Activity findOneActivity(Long Activity_id) {
        return activityRepository.findById(Activity_id)
                .orElseThrow(() -> new EntityNotFoundException("Activity with ID " + Activity_id + " not found."));
    }
}
