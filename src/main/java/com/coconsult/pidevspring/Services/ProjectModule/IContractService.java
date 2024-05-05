package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Contract;

import java.util.List;

public interface IContractService {
    List<Contract> getAllContracts();
    Contract findContractById(long id);

    Contract addContract(Contract contract);

    Contract updateContract(Contract contract);

    Contract getOneContract(long contractid);

    void removeContractbyid(long contractid);
    void deleteContract(Contract contract);
    Contract findByProjetContractProjectId(Long projectId);


}
