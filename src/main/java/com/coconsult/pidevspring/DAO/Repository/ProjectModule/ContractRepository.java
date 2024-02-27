package com.coconsult.pidevspring.DAO.Repository.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long> {
}
