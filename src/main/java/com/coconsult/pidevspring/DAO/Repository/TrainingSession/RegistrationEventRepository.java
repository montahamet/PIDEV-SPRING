package com.coconsult.pidevspring.DAO.Repository.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.RegistrationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationEventRepository extends JpaRepository<RegistrationEvent, Long> {
}
