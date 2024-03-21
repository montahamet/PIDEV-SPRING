package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Activity;
import com.coconsult.pidevspring.DAO.Entities.Event;

import java.util.List;

public interface IActivityService {
    List<Activity> findAllActivities();
    Activity addActivity(Activity activity);
    Activity UpdateActivity(Activity activity, Long eventId);
    public List<Event> getAllEventsWithName();
    void deleteActivityById (Long activity_id);
    Activity findOneActivity (Long Activity_id );
}
