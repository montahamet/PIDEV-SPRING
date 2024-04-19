package com.coconsult.pidevspring.DAO.Repository.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
    List<JobOffer> findByTitleJobOffer(String titleJobOffer);
    List<JobOffer> findByRequiredSkills(String requiredSkills);

}
