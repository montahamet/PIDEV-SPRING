package com.coconsult.pidevspring.RestControllers.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Invoice;
import com.coconsult.pidevspring.DAO.Entities.Project;
import com.coconsult.pidevspring.DAO.Repository.ProjectModule.ProjectRepository;
import com.coconsult.pidevspring.Services.ProjectModule.IInvoiceService;
import com.coconsult.pidevspring.Services.ProjectModule.IProjectService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

@RequestMapping("/Invoice")
public class InvoiceRestController {
    @Autowired
    IInvoiceService iInvoiceService;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    IProjectService projectService;
    @GetMapping("/GetAllInvoice")
    public List<Invoice> getAllInvoices(){
        return iInvoiceService.getAllInvoices();
    }
    @GetMapping("/GetInvoicebyid")
    public Invoice getOneInvoice(@RequestParam long invoiceid){
        return iInvoiceService.getOneInvoice(invoiceid);
    }
    @PostMapping("/AddInvoice/{projectId}")
    public Invoice addInvoice(@RequestBody Invoice invoice,@PathVariable Long projectId){
        System.out.println("***********************************"+invoice.toString());
        System.out.println("***********************************"+invoice.getInvoiceDescription());
        System.out.println("***********************************"+invoice.toString());
        System.out.println("***********************************"+projectId);
        Project p = new Project();
        p = projectService.getOneProject(projectId);
//        System.out.println("***********************************"+p);

        // Set the candidacy_id on the Interview object
        invoice.setProjetInvoice(p);
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
    @GetMapping("/GetInvoicebyPROJECT")
    public List<Invoice> getByProjetInvoiceProjectId(Long projectId) {
        return iInvoiceService.findByProjetInvoiceProjectId(projectId);
    }
}
