package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Entities.ContractEmployment;
import com.coconsult.pidevspring.DAO.Entities.Interview;
import com.coconsult.pidevspring.DAO.Repository.HR.CandidacyRepository;
import com.coconsult.pidevspring.DAO.Repository.HR.ContractEmploymentRepository;
import com.coconsult.pidevspring.DAO.Repository.HR.InterviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractEmploymentService implements IContractEmploymentService {
    @Autowired
    private CandidacyRepository candidacyRepository;

    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private ContractEmploymentRepository contractEmploymentRepository;

//    @Override
//    @Transactional
//    public ContractEmployment addContractByCandidacyId(Long candidacyId, ContractEmployment contract) {
//        Candidacy candidacy = candidacyRepository.findById(candidacyId)
//                .orElseThrow(() -> new IllegalArgumentException("Candidacy not found with id: " + candidacyId));
//
//        Interview interview = candidacy.getInterview();
//        if (interview != null && interview.isPassed()) {
//            contract.setCandidacy(candidacy);
//            return contractEmploymentRepository.save(contract);
//        } else {
//            throw new IllegalStateException("Cannot add contract: interview not passed or not available");
//        }
//    }
//@Override
//@Transactional
//public ContractEmployment addContractByInterviewId(Long interviewId, ContractEmployment contract) {
//    Interview interview = interviewRepository.findById(interviewId)
//            .orElseThrow(() -> new IllegalArgumentException("Interview not found with id: " + interviewId));
//
//    Candidacy candidacy = interview.getCandidacy();
//    if (candidacy == null) {
//        throw new IllegalStateException("Cannot add contract: no candidacy associated with the interview");
//    }
//
//    if (interview.isPassed()) {
//        contract.setCandidacy(candidacy);
//        contract.setInterview(interview); // Set the interview directly
//        return contractEmploymentRepository.save(contract);
//    } else {
//        throw new IllegalStateException("Cannot add contract: interview not passed or not available");
//    }
//}
    @Override
    public List<ContractEmployment> findAllContractEmployments() {
        return contractEmploymentRepository.findAll();
    }
}
