package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.RegistrationEvent;
import com.coconsult.pidevspring.DAO.Entities.RegistrationTS;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.RegistrationTSRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationTSService implements IRegistrationTSService{
    @Autowired

    RegistrationTSRepository registrationTSRepository;
    @Override
    public List<RegistrationTS> findAllRegistrationTS() {
        return registrationTSRepository.findAll();
    }

    @Override
    public RegistrationTS UpdateRegistrationTS(RegistrationTS registrationTS) {
        return registrationTSRepository.save(registrationTS);
    }

    @Override
    public RegistrationTS addRegistrationTS(RegistrationTS registrationTS) {
        return registrationTSRepository.save(registrationTS);
    }



    @Override
    public void deleteRegistrationTSById(Long registrationTS_id) {
        registrationTSRepository.deleteById(registrationTS_id);
    }

    @Override
    public RegistrationTS findOneRegistrationTS(Long registrationTS_id) {
        return registrationTSRepository.findById(registrationTS_id).get();
    }
}
