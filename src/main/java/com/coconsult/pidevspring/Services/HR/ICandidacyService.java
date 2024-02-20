package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;

import java.util.List;

public interface ICandidacyService {
    Candidacy addCandidacy(Candidacy candidacy);
    List<Candidacy> addAllCandidacies(List<Candidacy> Candidacys);
    Candidacy updateCandidacy(Candidacy candidacy);
    List<Candidacy> findAllCandidacies();
    Candidacy findCandidacyById(long id);
    void deleteCandidacy(Candidacy candidacy);
    void deleteCandidacyById(long id);
}
