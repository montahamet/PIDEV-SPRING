package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Activity;
import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.ActivityRepository;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActivityService implements IActivityService {
    ActivityRepository activityRepository;
    EventRepository eventRepository;

    @Override
    public List<Activity> findAllActivities() {
        return activityRepository.findAll();
    }

    @Override 
    public Activity addActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Activity UpdateActivity(Activity activity) {
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
            event.setEvent_name(event.getEvent_name()); // Assuming getName() returns the name of the event
        }
        return events;
    }



    @Override
    public Activity findOneActivity(Long Activity_id) {
        return activityRepository.findById(Activity_id).get();
    }
}
