package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.RegistrationEvent;
import com.coconsult.pidevspring.DAO.Entities.RegistrationTS;

import java.util.List;

public interface IRegistrationTSService {
    List<RegistrationTS> findAllRegistrationTS();
    RegistrationTS addRegistrationTS(RegistrationTS registrationTS);
    RegistrationTS updateRegistrationTS(RegistrationTS registrationTS);
    void deleteRegistrationTSById (Long registrationTS_id);
    RegistrationTS findOneRegistrationTS (Long registrationTS_id);
}
