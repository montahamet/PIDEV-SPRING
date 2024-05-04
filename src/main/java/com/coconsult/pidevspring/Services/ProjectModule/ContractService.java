package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Contract;
import com.coconsult.pidevspring.DAO.Repository.ProjectModule.ContractRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContractService implements IContractService
{
    @Autowired

    ContractRepository contractRepository;

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public Contract findContractById(long id) {
        return contractRepository.findById(id).get();
    }

    @Override
    public Contract addContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract updateContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract getOneContract(long contractid) {
        return contractRepository.findById(contractid).get();
    }

    @Override
    public void removeContractbyid(long contractid) {
        contractRepository.deleteById(contractid);

    }

    @Override
    public void deleteContract(Contract contract) {
        contractRepository.delete(contract);

    }

    @Override
    public Contract findByProjetContractProjectId(Long projectId) {
        return contractRepository.findByProjetContractProjectId(projectId);
    }


}
