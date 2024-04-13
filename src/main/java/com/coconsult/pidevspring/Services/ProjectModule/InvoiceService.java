package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Invoice;
import com.coconsult.pidevspring.DAO.Repository.ProjectModule.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceService implements IInvoiceService {
    InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }



    @Override
    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getOneInvoice(long invoiceid) {
        return invoiceRepository.findById(invoiceid).get();
    }

    @Override
    public void removeInvoicebyid(long invoiceid) {
        invoiceRepository.deleteById(invoiceid);

    }

    @Override
    public void deleteInvoice(Invoice invoice) {
        invoiceRepository.delete(invoice);

    }
}
