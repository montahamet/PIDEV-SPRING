package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;

public interface ICandidacyService {
    Candidacy addOrUpdateCandidacy(Candidacy candidacy);
    List<Candidacy> addAllCandidacies(List<Candidacy> Candidacys);
    List<Candidacy> findAllCandidacies();
    Candidacy findCandidacyById(long id);
    void deleteCandidacy(Candidacy candidacy);
    void deleteCandidacyById(long id);
//    Candidacy addCandidate(Candidacy candidacy);
//
//    List<JobOffer> getAllJobOffersWithId();
//
//    Candidacy findOneCandidacy(Long candidacy_id);
//     List<Candidacy> getCandidaturesParIdJob(Long jobOffer_id);
//    List<Candidacy> findCandidaciesByJobOfferTitle(String titleJobOffer);
List<Candidacy> getCandidaciesByJobOfferId(Long jobOfferId);
     List<Candidacy> findAllByJobOfferId(Long jobOfferId) ;
     int countCandidaciesByJobOfferId(Long jobOfferId) ;
     Candidacy updateCandidacyStatus(Candidacy candidacy);



    }
