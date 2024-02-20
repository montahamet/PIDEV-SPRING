package com.coconsult.pidevspring.DAO.Repository.HR;

import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
    List<JobOffer> findByTitleJobOffer(String titleJobOffer);
    List<JobOffer> findByRequiredSkills(String requiredSkills);
}
