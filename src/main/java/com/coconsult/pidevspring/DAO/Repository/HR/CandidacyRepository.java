package com.coconsult.pidevspring.DAO.Repository.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidacyRepository extends JpaRepository<Candidacy, Long> {
//    @Query("SELECT c FROM Candidacy c WHERE c.job_offer.jobOffer_id = :jobOfferId")
//    List<Candidacy> findByJobOfferId(Long jobOfferId);
//    @Query("SELECT c FROM Candidacy c WHERE c.job_offer.titleJobOffer = :titleJobOffer")
//    List<Candidacy> findByJobOfferTitle(String titleJobOffer);
@Query("SELECT c FROM Candidacy c WHERE c.jobOffer.jobOffer_id = :jobOfferId")
List<Candidacy> findByJobOfferId(Long jobOfferId);
}
