package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.FeedBack;
import com.coconsult.pidevspring.DAO.Entities.RegistrationEvent;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.RegistrationEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationEventService implements IRegistrationEventService{
    RegistrationEventRepository registrationEventRepository;
    @Override
    public List<RegistrationEvent> findAllRegistrationEvent() {
        return registrationEventRepository.findAll();
    }

    @Override
    public RegistrationEvent addRegistrationEvent(RegistrationEvent registrationEvent) {
        return registrationEventRepository.save(registrationEvent);
    }

    @Override
    public RegistrationEvent updateRegistrationEvent(RegistrationEvent registrationEvent) {
        return registrationEventRepository.save(registrationEvent);
    }

    @Override
    public void deleteRegistrationEvent(RegistrationEvent registrationEvent) {
        registrationEventRepository.delete(registrationEvent);
    }

    @Override
    public RegistrationEvent findOneRegistrationEvent(Long registrationE_id) {
        return registrationEventRepository.findById(registrationE_id).get();
    }
}
