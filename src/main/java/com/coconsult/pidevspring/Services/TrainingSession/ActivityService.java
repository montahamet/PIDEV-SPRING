package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Activity;
import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.ActivityRepository;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.EventRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ActivityService implements IActivityService {
    @Autowired
    ActivityRepository activityRepository;
    EventRepository eventRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(ActivityService.class);


    @Override
    public Page<Activity> findAllActivities(Pageable pageable) {
        return activityRepository.findAll(pageable);
    }

    @Override
    public Activity addActivity(Activity activity) {
        return activityRepository.save(activity);
    }


    @Override
    public Activity updateActivity(Long activity_id, Activity activityDetails, Long event_id) {
        // Trouver l'activité existante
        Activity activityToUpdate = activityRepository.findById(activity_id)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found with id: " + activity_id));

        // Trouver l'événement à associer
        Event event = eventRepository.findById(event_id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + event_id));

        // Mettre à jour les champs de l'activité avec les nouvelles valeurs
        activityToUpdate.setActivity_name(activityDetails.getActivity_name());
        activityToUpdate.setDescription(activityDetails.getDescription());
        activityToUpdate.setStartTime(activityDetails.getStartTime());
        activityToUpdate.setFinishTime(activityDetails.getFinishTime());
        // Associer l'événement
        activityToUpdate.setEvent(event);

        // Sauvegarder et retourner l'activité mise à jour
        return activityRepository.save(activityToUpdate);
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

    @Override
    public List<Activity> searchActivities(String keywords, LocalDate startDate, LocalDate endDate) {
        String jpql = "select a from Activity a where ";
        boolean firstCondition = true;

        if (keywords != null && !keywords.isEmpty()) {
            // Assurez-vous que le nom du champ est correct. Basé sur votre classe, cela devrait être `Activity_name` et non `name`.
            jpql += "lower(a.Activity_name) like lower(concat('%', :keywords, '%')) ";
            firstCondition = false;
        }
        if (startDate != null) {
            if (!firstCondition) jpql += "and ";
            jpql += "a.startTime >= :startDate ";
            firstCondition = false;
        }
        if (endDate != null) {
            if (!firstCondition) jpql += "and ";
            // Utilisez le nom correct `finishTime` correspondant à votre entité
            jpql += "a.finishTime <= :endDate";
        }

        TypedQuery<Activity> query = entityManager.createQuery(jpql, Activity.class);

        if (keywords != null && !keywords.isEmpty()) {
            query.setParameter("keywords", keywords);
        }
        if (startDate != null) {
            query.setParameter("startDate", startDate.atStartOfDay());
        }
        if (endDate != null) {
            query.setParameter("endDate", endDate.atTime(LocalTime.MAX));
        }

        return query.getResultList();
    }
}
