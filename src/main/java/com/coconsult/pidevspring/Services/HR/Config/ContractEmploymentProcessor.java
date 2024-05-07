package com.coconsult.pidevspring.Services.HR.Config;

import com.coconsult.pidevspring.DAO.Entities.ContractEmployment;
import org.springframework.batch.item.ItemProcessor;

public class ContractEmploymentProcessor implements ItemProcessor<ContractEmployment,ContractEmployment> {
    @Override
    public ContractEmployment process(ContractEmployment item) throws Exception {
        return item;
    }
}
