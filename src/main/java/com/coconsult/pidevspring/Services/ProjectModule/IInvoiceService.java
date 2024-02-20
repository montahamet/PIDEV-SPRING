package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Invoice;

import java.util.List;

public interface IInvoiceService {
    List<Invoice> getAllInvoices();

    Invoice addInvoice(Invoice invoice);

    Invoice updateInvoice(Invoice invoice);

    Invoice getOneInvoice(long invoiceid);

    void removeInvoicebyid(long invoiceid);
    void deleteInvoice(Invoice invoice);
}
