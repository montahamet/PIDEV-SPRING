package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.RegistrationEvent;
import com.coconsult.pidevspring.DAO.Entities.RegistrationTS;

import java.util.List;

public interface IRegistrationTSService {
    List<RegistrationTS> findAllRegistrationTS();
    RegistrationTS UpdateRegistrationTS(RegistrationTS registrationTS);
    RegistrationTS addRegistrationTS(Long ts_id , Long userId);

    void deleteRegistrationTSById (Long registrationTS_id);
    RegistrationTS findOneRegistrationTS (Long registrationTS_id);

    boolean isUserRegistered(Long sessionId, Long userId);
    public boolean unregisterFromTraining(Long userId, Long sessionId) ;
    }
