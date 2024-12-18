package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;
import java.util.Map;

public interface ICandidacyService {
    Candidacy addOrUpdateCandidacy(Candidacy candidacy);
    List<Candidacy> addAllCandidacies(List<Candidacy> Candidacys);
     String getCandidateNameByCandidacyId(Long candidacyId);

        List<Candidacy> findAllCandidacies();
    Candidacy findCandidacyById(long id);
    void deleteCandidacy(Candidacy candidacy);
    void deleteCandidacyById(long id);
Candidacy addCandidate(Candidacy candidacy,Long id);

List<Candidacy> getCandidaciesByJobOfferId(Long jobOfferId);
     List<Candidacy> findAllByJobOfferId(Long jobOfferId) ;
     int countCandidaciesByJobOfferId(Long jobOfferId) ;
     Candidacy updateCandidacyStatus(Candidacy candidacy,Long id);
     String getCandidacyInfoFromLinkedIn(String linkedinUrl) throws UnirestException;;

    void updateCandidaciesWithLinkedInData();
    List<Object[]> getCandidatesByCountryStatistics();
     List<Map<String, Object>> getMostQualifiedCandidatesStatistics();

     void updateVerifEmailForAllCandidacies();


    }
