package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.FeedBack;
import com.coconsult.pidevspring.DAO.Entities.RegistrationEvent;

import java.util.List;

public interface IRegistrationEventService {
    List<RegistrationEvent> findAllRegistrationEvent();
    RegistrationEvent addRegistrationEvent(RegistrationEvent registrationEvent);
    RegistrationEvent UpdateRegistrationEvent(RegistrationEvent registrationEvent);

    void deleteRegistrationEventById (Long registrationE_id);
    RegistrationEvent findOneRegistrationEvent (Long registrationE_id);
    public RegistrationEvent registerForEvent(Long eventId, Long userId);
}
