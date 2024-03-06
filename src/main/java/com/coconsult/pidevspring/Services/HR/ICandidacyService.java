package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;

import java.util.List;

public interface ICandidacyService {
    Candidacy addOrUpdateCandidacy(Candidacy candidacy);
    List<Candidacy> addAllCandidacies(List<Candidacy> Candidacys);
    List<Candidacy> findAllCandidacies();
    Candidacy findCandidacyById(long id);
    void deleteCandidacy(Candidacy candidacy);
    void deleteCandidacyById(long id);
}
