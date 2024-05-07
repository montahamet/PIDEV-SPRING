package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.ContractEmployment;

import java.util.List;

public interface IContractEmploymentService {
//     ContractEmployment addContractByCandidacyId(Long candidacyId, ContractEmployment contract);
// ContractEmployment addContractByInterviewId(Long interviewId, ContractEmployment contract);
     List<ContractEmployment> findAllContractEmployments();



}
