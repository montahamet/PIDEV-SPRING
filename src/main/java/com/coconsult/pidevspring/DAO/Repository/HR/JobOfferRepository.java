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
//    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Candidacy a WHERE a.job_offer.jobOffer_id = :jobofferId")
//    boolean existsByJobOfferId(Long jobofferId);
//    @Query("SELECT j FROM JobOffer j LEFT JOIN FETCH j.Candidacys WHERE j.jobOffer_id = :jobOfferId")
//    Optional<JobOffer> findByIdWithCandidacies(@Param("jobOfferId") Long jobOfferId);
//    @Query("SELECT c FROM Candidacy c JOIN c.job_offer j WHERE j.jobOffer_id = :jobOfferId")
//    Optional<JobOffer> findAllCandidaciesByJobOfferId(@Param("jobOfferId") Long jobOfferId);

}