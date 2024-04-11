package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Activity;
import com.coconsult.pidevspring.DAO.Entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IActivityService {
    Page<Activity> findAllActivities(Pageable pageable);
    Activity addActivity(Activity activity);
    public Activity updateActivity(Long activity_id, Activity activityDetails, Long event_id) ;  
    public List<Event> getAllEventsWithName();

    public List<Activity> searchActivities(String keywords, LocalDate startDate, LocalDate endDate);
    void deleteActivityById (Long activity_id);
    Activity findOneActivity (Long Activity_id );
    public List<Activity> findAllActivities() ;
    }
