package com.coconsult.pidevspring.DAO.Repository.HR;

import com.coconsult.pidevspring.DAO.Entities.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findByDateInterview(LocalDateTime dateInterview);
}
