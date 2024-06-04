package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.*;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.RegistrationTSRepository;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.TrainingSessionRepository;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class RegistrationTSService implements IRegistrationTSService{
    private static final Logger log = Logger.getLogger(RegistrationEventService.class.getName());

    @Autowired

    RegistrationTSRepository registrationTSRepository;
    UserRepository userRepository;
    TrainingSessionRepository trainingSessionRepository;
    @Override
    public List<RegistrationTS> findAllRegistrationTS() {
        return registrationTSRepository.findAll();
    }

    @Override
    public RegistrationTS UpdateRegistrationTS(RegistrationTS registrationTS) {
        return registrationTSRepository.save(registrationTS);
    }
    @Override

    @Transactional
    public RegistrationTS addRegistrationTS(Long tsId, Long userId) {
        log.log(Level.INFO, "Attempting to add registration for training session ID: {0} and user ID: {1}", new Object[]{tsId, userId});

        // Fetching the training session and user entities by their IDs
        TrainingSession trainingSession = trainingSessionRepository.findById(tsId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Training session not found"));
        trainingSession.setRegisteredCount(trainingSession.getRegisteredCount()+1);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Check if the user is already registered for this training session
        boolean exists = registrationTSRepository.existsByTrainingSessionIdAndUserId(tsId, userId);
        if (exists) {
            log.log(Level.WARNING, "User already registered for this training session. TSId: {0}, UserId: {1}", new Object[]{tsId, userId});
            throw new IllegalStateException("User is already registered for this training session");
        }

        // Create a new RegistrationTS object
        RegistrationTS registrationTS = new RegistrationTS();
        registrationTS.setTrainingSession(trainingSession);
        registrationTS.setUser(user);
        registrationTS.setRegistrationTS_date(LocalDateTime.now()); // Set the current system date and time
        registrationTS.setRegistrationTS_status(Status.valueOf("PENDING")); // Set the status to PENDING

        // Save the RegistrationTS object
        RegistrationTS savedRegistrationTS = registrationTSRepository.save(registrationTS);

        log.log(Level.INFO, "Registration successful for training session ID: {0}, user ID: {1}", new Object[]{tsId, userId});
        return savedRegistrationTS;
    }
    @Override
    public void deleteRegistrationTSById(Long registrationTS_id) {
        registrationTSRepository.deleteById(registrationTS_id);
    }

    @Override
    public RegistrationTS findOneRegistrationTS(Long registrationTS_id) {
        return registrationTSRepository.findById(registrationTS_id).get();
    }
    public boolean isUserRegistered(Long tsId, Long userId) {
        return registrationTSRepository.existsByTrainingSessionIdAndUserId(tsId, userId);
    }
    public boolean unregisterFromTraining(Long userId, Long sessionId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        TrainingSession session = trainingSessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found"));

        user.getTrainingSessions().remove(session);
        userRepository.save(user);
        return true;
    }
}
