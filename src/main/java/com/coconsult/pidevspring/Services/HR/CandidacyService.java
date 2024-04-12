package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Repository.HR.CandidacyRepository;
import com.coconsult.pidevspring.DAO.Repository.HR.JobOfferRepository;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
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


    @Override
    public List<Candidacy> getCandidaciesByJobOfferId(Long jobOfferId) {
        // Implement the logic to retrieve candidacies by job offer ID from the repository
        return candidacyRepository.findByJobOfferId
                (jobOfferId);
    }
    public List<Candidacy> findAllByJobOfferId(Long jobOfferId) {
        return candidacyRepository.findByJobOfferId(jobOfferId);
    }
    @Override
    public int countCandidaciesByJobOfferId(Long jobOfferId) {
        return candidacyRepository.countByJobOfferId(jobOfferId);
    }
    public Candidacy updateCandidacyStatus(Candidacy updatedCandidacy) {
        // Fetch the existing Candidacy object from the database
        Candidacy existingCandidacy = candidacyRepository.findById(updatedCandidacy.getCandidacy_id()).orElse(null);

        if (existingCandidacy != null) {
            // Update the status of the existing Candidacy object
            existingCandidacy.setCandidacystatus(updatedCandidacy.getCandidacystatus());

            // Save the updated Candidacy object back to the database
            return candidacyRepository.save(existingCandidacy);
        } else {
            // Handle the case where the Candidacy object is not found
            // You may choose to throw an exception or return null
            return null;
        }
    }

}
