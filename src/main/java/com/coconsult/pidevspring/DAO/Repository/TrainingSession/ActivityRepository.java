package com.coconsult.pidevspring.DAO.Repository.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity,Long> {
}
