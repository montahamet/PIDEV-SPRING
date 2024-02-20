package com.coconsult.pidevspring.RestControllers.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Documentation;
import com.coconsult.pidevspring.Services.ProjectModule.IDocumentationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

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
    public Documentation addDoc(@RequestBody Documentation doc){
        return iDocumentationService.addDoc(doc);
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
