package com.coconsult.pidevspring.DAO.Repository.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    @Query("SELECT i FROM Invoice i WHERE i.projetInvoice.projectId = :projectId")
    List<Invoice> findByProjetInvoiceProjectId(Long projectId);
}
