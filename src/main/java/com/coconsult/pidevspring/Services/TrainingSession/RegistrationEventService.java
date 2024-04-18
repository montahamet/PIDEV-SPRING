package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.*;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.EventRepository;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.RegistrationEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RegistrationEventService implements IRegistrationEventService{
    RegistrationEventRepository registrationEventRepository;
    EventRepository eventRepository;
    @Override
    public List<RegistrationEvent> findAllRegistrationEvent() {
        return registrationEventRepository.findAll();
    }

    @Override
    public RegistrationEvent addRegistrationEvent(RegistrationEvent registrationEvent) {
        return registrationEventRepository.save(registrationEvent);
    }

    @Override
    public RegistrationEvent UpdateRegistrationEvent(RegistrationEvent registrationEvent) {
        return registrationEventRepository.save(registrationEvent);
    }


    @Override
    public void deleteRegistrationEventById(Long registrationE_id) {
        registrationEventRepository.deleteById(registrationE_id);
    }

    @Override
    public RegistrationEvent findOneRegistrationEvent(Long registrationE_id) {
        return registrationEventRepository.findById(registrationE_id).get();
    }

    @Override
    public RegistrationEvent registerForEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));

        RegistrationEvent registration = new RegistrationEvent();
        registration.setEvent(event);
//        registration.setUser(user);
        registration.setRegistration_date(LocalDateTime.now()); // Set system date as registration date
        registration.setRegistrationEvent_status(Status.PENDING);

        // Save the new registration
        return registrationEventRepository.save(registration);    }
}
