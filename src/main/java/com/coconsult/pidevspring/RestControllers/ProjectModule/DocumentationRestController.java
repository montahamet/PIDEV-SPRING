package com.coconsult.pidevspring.RestControllers.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Documentation;
import com.coconsult.pidevspring.Services.ProjectModule.IDocumentationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

@RequestMapping("/Documents")
public class DocumentationRestController {
    IDocumentationService iDocumentationService;
    @GetMapping("/GetAllDocs")
    public List<Documentation> getAllDocs(){
        return iDocumentationService.getAllDocs();
    }
    @GetMapping("/GetDocByid")
    public Documentation findDocById(@RequestParam long id){
        return iDocumentationService.findDocById(id);
    }
    @PostMapping("/AddDoc")
    public Documentation addDoc( @ModelAttribute Documentation doc, @RequestParam("rapportFile") MultipartFile rapportFile,@RequestParam("specficationDocFile") MultipartFile specficationDocFile) {
        return iDocumentationService.addDoc(doc,rapportFile,specficationDocFile);
    }

        @PutMapping("/UpdateDoc")
    public Documentation updateDoc(@RequestBody Documentation doc){
        return iDocumentationService.updateDoc(doc);
    }
    @DeleteMapping("/DeleteDocbyid")
    public void removeDocbyid(@RequestParam long id){
        iDocumentationService.removeDocbyid(id);
    }
    @DeleteMapping("/DeleteDoc")
    public void deleteDoc(Documentation doc){
        iDocumentationService.deleteDoc(doc);
    }
}
