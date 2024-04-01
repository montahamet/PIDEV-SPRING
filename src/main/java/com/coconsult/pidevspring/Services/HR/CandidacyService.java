package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import com.coconsult.pidevspring.DAO.Repository.HR.CandidacyRepository;
import com.coconsult.pidevspring.DAO.Repository.HR.JobOfferRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CandidacyService implements ICandidacyService {
    @Autowired
    CandidacyRepository candidacyRepository;
    JobOfferRepository jobOfferRepository;

    @Override
    public Candidacy addOrUpdateCandidacy(Candidacy candidacy) {
        return candidacyRepository.save(candidacy);
    }

    @Override
    public List<Candidacy> addAllCandidacies(List<Candidacy> Candidacys) {
        return candidacyRepository.saveAll(Candidacys);
    }
//    @Override
//    public Candidacy addCandidate(Candidacy candidacy) {
//        return candidacyRepository.save(candidacy);
//    }

    @Override
    public List<Candidacy> findAllCandidacies() {
        return candidacyRepository.findAll();
    }

    @Override
    public Candidacy findCandidacyById(long id) {
        return candidacyRepository.findById(id).get();
    }

    @Override
    public void deleteCandidacy(Candidacy candidacy) {
        candidacyRepository.delete(candidacy);

    }

    @Override
    public void deleteCandidacyById(long id) {
        candidacyRepository.deleteById(id);

    }
//    @Override
//    public List<JobOffer> getAllJobOffersWithId() {
//        List<JobOffer> jobs = jobOfferRepository.findAll();
//        for (JobOffer job : jobs) {
//            job.setCandidacys(job.getCandidacys());
//        }
//        return jobs;
//    }
//    @Override
//    public List<Candidacy> getCandidaturesParIdJob(Long jobOffer_id) {
//        return candidacyRepository.findByJobOfferId(jobOffer_id);
//    }
//
//
//    @Override
//    public Candidacy findOneCandidacy(Long candidacy_id) {
//        return candidacyRepository.findById(candidacy_id)
//                .orElseThrow(() -> new EntityNotFoundException("Candidacy with ID " + candidacy_id + " not found."));
//    }
//    public List<Candidacy> findCandidaciesByJobOfferTitle(String titleJobOffer) {
//        return candidacyRepository.findByJobOfferTitle(titleJobOffer);
//    }


    @Override
    public List<Candidacy> getCandidaciesByJobOfferId(Long jobOfferId) {
        // Implement the logic to retrieve candidacies by job offer ID from the repository
        return candidacyRepository.findByJobOfferId
                (jobOfferId);
    }
}
