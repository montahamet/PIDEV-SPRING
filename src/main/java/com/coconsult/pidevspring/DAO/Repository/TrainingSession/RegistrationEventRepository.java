package com.coconsult.pidevspring.DAO.Repository.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Entities.RegistrationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RegistrationEventRepository extends JpaRepository<RegistrationEvent, Long> {
    Set<RegistrationEvent> findByEvent(Event event);
}
