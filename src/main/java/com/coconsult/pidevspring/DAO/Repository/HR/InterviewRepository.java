package com.coconsult.pidevspring.DAO.Repository.HR;

import com.coconsult.pidevspring.DAO.Entities.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findByDateInterview(LocalDateTime dateInterview);

    @Query("SELECT i FROM Interview i WHERE i.candidacy.candidacy_id = :candidacyId")
    List<Interview> findByCandidacyId(@Param("candidacyId") Long candidacyId);
}
