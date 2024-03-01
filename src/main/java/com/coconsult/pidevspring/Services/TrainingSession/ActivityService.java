package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Activity;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActivityService implements IActivityService {
    ActivityRepository activityRepository;

    @Override
    public List<Activity> findAllActivities() {
        return activityRepository.findAll();
    }

    @Override 
    public Activity addActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Activity updateActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public void deleteActivity(Activity activity) {
         activityRepository.delete(activity);
    }

    @Override
    public Activity findOneActivity(Long Activity_id) {
        return activityRepository.findById(Activity_id).get();
    }
}
