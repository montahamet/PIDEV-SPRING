package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.Services.HR.ICandidacyService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/candidacy")
public class CandidacyRestController {
    ICandidacyService iCandidacyService;

    @PostMapping("addCandidacy")
    public Candidacy addCandidacy (@RequestBody Candidacy Candidacy){
        return iCandidacyService.addCandidacy(Candidacy);
    }


    @PutMapping("updateCandidacy")
    public Candidacy updateCandidacy (@RequestBody  Candidacy Candidacy){
        return iCandidacyService.updateCandidacy(Candidacy);
    }

    @PostMapping("addAll")
    public List<Candidacy> addAllCandidacies(@RequestBody List<Candidacy> Candidacys){
        return iCandidacyService.addAllCandidacies(Candidacys);
    }

    @GetMapping("getCandidacy/{id}")
    public Candidacy getCandidacy(@PathVariable("id") long id){
        return iCandidacyService.findCandidacyById(id);
    }
    @GetMapping("findAllCandidacies")
    public List<Candidacy> findAllCandidacies() {
        return iCandidacyService.findAllCandidacies();
    }

    @DeleteMapping("deleteCandidacy")
    public void deleteCandidacy(@RequestBody Candidacy Candidacy){
        iCandidacyService.deleteCandidacy(Candidacy);
    }

    @DeleteMapping("deleteById/{id}")
    public void deleteCandidacyById(@PathVariable("id") long id){
        iCandidacyService.deleteCandidacyById(id);
    }
}
