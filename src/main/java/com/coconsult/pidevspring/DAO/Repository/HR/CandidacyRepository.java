package com.coconsult.pidevspring.DAO.Repository.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import com.coconsult.pidevspring.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CandidacyRepository extends JpaRepository<Candidacy, Long> {

@Query("SELECT c FROM Candidacy c WHERE c.jobOffer.jobOffer_id = :jobOfferId")
List<Candidacy> findByJobOfferId(Long jobOfferId);
    @Query("SELECT COUNT(c) FROM Candidacy c WHERE c.jobOffer.jobOffer_id = :jobOfferId")
    int countByJobOfferId(Long jobOfferId);
    Candidacy findByLinkedin(String linkedin);
    List<Candidacy> findByLinkedinIsNotNull(); // Add this method to retrieve candidacies where linkedin is not null
    @Query("SELECT c.country, COUNT(c) FROM Candidacy c GROUP BY c.country")
    List<Object[]> getCandidatesByCountryStatistics();
    @Query("SELECT c.jobOffer.titleJobOffer, COUNT(c) FROM Candidacy c WHERE c.jobOffer IS NOT NULL GROUP BY c.jobOffer")
    List<Object[]> getCandidatesByJobOfferStatistics();
    Optional<Candidacy> findByEmail(String email);
    Candidacy findCandidacyByEmail(String email) ;
    Boolean existsByEmail(String email);
}
