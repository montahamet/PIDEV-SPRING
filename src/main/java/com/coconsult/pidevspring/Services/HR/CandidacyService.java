package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Repository.HR.CandidacyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CandidacyService implements ICandidacyService {
    CandidacyRepository candidacyRepository;
    @Override
    public Candidacy addCandidacy(Candidacy candidacy) {
        return candidacyRepository.save(candidacy);
    }

    @Override
    public List<Candidacy> addAllCandidacies(List<Candidacy> Candidacys) {
        return candidacyRepository.saveAll(Candidacys);
    }

    @Override
    public Candidacy updateCandidacy(Candidacy candidacy) {
        return candidacyRepository.save(candidacy);
    }

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
}
