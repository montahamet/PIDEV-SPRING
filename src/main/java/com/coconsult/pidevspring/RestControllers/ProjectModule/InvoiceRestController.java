package com.coconsult.pidevspring.RestControllers.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Invoice;
import com.coconsult.pidevspring.Services.ProjectModule.IInvoiceService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("/Invoice")
public class InvoiceRestController {
    IInvoiceService iInvoiceService;
    @GetMapping("/GetAllInvoice")
    public List<Invoice> getAllInvoices(){
        return iInvoiceService.getAllInvoices();
    }
    @GetMapping("/GetInvoicebyid")
    public Invoice getOneInvoice(@RequestParam long invoiceid){
        return iInvoiceService.getOneInvoice(invoiceid);
    }
    @PostMapping("/AddInvoice")
    public Invoice addInvoice(@RequestBody Invoice invoice){
        return iInvoiceService.addInvoice(invoice);
    }
    @PutMapping("/UpdateInvoice")
    public Invoice updateInvoice(@RequestBody Invoice invoice){
        return iInvoiceService.updateInvoice(invoice);
    }
    @DeleteMapping("/DeleteByid")
    public void removeInvoicebyid(@RequestParam long invoiceid){
        iInvoiceService.removeInvoicebyid(invoiceid);
    }
    @DeleteMapping("/DeleteInvoice")
    public void deleteInvoice(@RequestBody Invoice invoice){
        iInvoiceService.deleteInvoice(invoice);
    }
}
