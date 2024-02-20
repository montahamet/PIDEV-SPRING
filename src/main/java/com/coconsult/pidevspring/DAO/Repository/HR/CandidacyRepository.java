package com.coconsult.pidevspring.DAO.Repository.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidacyRepository extends JpaRepository<Candidacy, Long> {
}
