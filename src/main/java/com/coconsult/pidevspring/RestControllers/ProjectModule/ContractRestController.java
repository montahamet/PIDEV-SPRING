package com.coconsult.pidevspring.RestControllers.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Contract;
import com.coconsult.pidevspring.Services.ProjectModule.IContractService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

@RequestMapping("/Contract")
public class ContractRestController {
    IContractService iContractService;
    @GetMapping("/getAllContracts")
    public List<Contract> findAllContracts() {
        return iContractService.getAllContracts();
    }
    @GetMapping("/getContractById")
    public Contract findContractById(@RequestParam long id){
        return iContractService.findContractById(id);
    }
    @PostMapping("/AddContract")
    public Contract addContract(@RequestBody Contract contract){
        return iContractService.addContract(contract);
    }
    @PutMapping("/UpdateContract")
    public Contract updateContract(@RequestBody Contract contract){
        return iContractService.updateContract(contract);
    }
    @DeleteMapping("/DeleteContractByid")
    public void removeContractbyid(@RequestParam long contractid){
        iContractService.removeContractbyid(contractid);
    }
    @DeleteMapping("/DeleteContract")
    public void deleteContract(@RequestBody Contract contract){
        iContractService.deleteContract(contract);
    }

    @GetMapping("/GetContractbyPROJECT")
    public Contract findByProjetContractProjectId(Long projectId) {
        return iContractService.findByProjetContractProjectId(projectId);
    }
}
