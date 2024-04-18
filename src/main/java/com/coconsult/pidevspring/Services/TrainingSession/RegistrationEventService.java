package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.*;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.EventRepository;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.RegistrationEventRepository;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
<<<<<<< HEAD
=======
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
>>>>>>> 340bb1611de4d28d73c923a57941f8b1cd8d1183
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class RegistrationEventService implements IRegistrationEventService{
<<<<<<< HEAD
=======
    private static final Logger log = Logger.getLogger(RegistrationEventService.class.getName());

    @Autowired


>>>>>>> 340bb1611de4d28d73c923a57941f8b1cd8d1183
    RegistrationEventRepository registrationEventRepository;
   UserRepository userRepository;



    EventRepository eventRepository;
    @Override
    public List<RegistrationEvent> findAllRegistrationEvent() {
        return registrationEventRepository.findAll();
    }


    @Override
    @Transactional
    public RegistrationEvent addRegistrationEvent(Long eventId, Long userId) {
        log.log(Level.INFO, "Attempting to add registration event for eventId: {0} and userId: {1}", new Object[]{eventId, userId});

        Optional<Event> event = eventRepository.findById(eventId);
        Optional<User> user = userRepository.findById(userId);

        if (event.isEmpty() || user.isEmpty()) {
            log.log(Level.WARNING, "Event or User not found for eventId: {0} and userId: {1}", new Object[]{eventId, userId});
            throw new OpenApiResourceNotFoundException("Event or User not found");
        }

        boolean exists = registrationEventRepository.existsByEvent_EventIdAndUser_UserId(eventId, userId);
        if (exists) {
            log.log(Level.WARNING, "User already registered for event. EventId: {0}, UserId: {1}", new Object[]{eventId, userId});
            throw new IllegalStateException("User is already registered for this event");
        }

        // Create a new RegistrationEvent object
        RegistrationEvent registrationEvent = new RegistrationEvent();

        // Set the event and user for the registration event
        registrationEvent.setEvent(event.get());
        registrationEvent.setUser(user.get());

        // Set the locked field to false
        registrationEvent.setLocked(false);

        // Save the RegistrationEvent object
        RegistrationEvent savedRegistrationEvent = registrationEventRepository.save(registrationEvent);

        log.log(Level.INFO, "Registration successful for eventId: {0}, userId: {1}", new Object[]{eventId, userId});

        // Return the saved RegistrationEvent object
        return savedRegistrationEvent;
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
