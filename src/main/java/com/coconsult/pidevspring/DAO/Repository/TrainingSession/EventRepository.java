package com.coconsult.pidevspring.DAO.Repository.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
