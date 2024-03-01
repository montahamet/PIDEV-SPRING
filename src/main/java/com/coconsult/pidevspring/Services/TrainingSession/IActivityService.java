package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Activity;

import java.util.List;

public interface IActivityService {
    List<Activity> findAllActivities();
    Activity addActivity(Activity activity);
    Activity updateActivity(Activity activity);
    void deleteActivityById (Long Activity_id);
    Activity findOneActivity (Long Activity_id );
}
