package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import com.coconsult.pidevspring.Services.HR.ICandidacyService;
import com.coconsult.pidevspring.Services.HR.IJobOfferService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
@RequestMapping("/candidacy")
public class CandidacyRestController {
    @Autowired
    ICandidacyService iCandidacyService;
    IJobOfferService iJobOfferService;

    @PostMapping("addCandidacy")
    public Candidacy addCandidacy (@RequestBody Candidacy Candidacy){
        return iCandidacyService.addOrUpdateCandidacy(Candidacy);
    }

//    @PostMapping("/addCandidacy/{jobOffer_id}")
//    public Candidacy addCandidacy(@RequestBody Candidacy candidacy, @PathVariable("jobOffer_id") long jobOffer_id) {
//        JobOffer job = iJobOfferService.findOneJobOffer(jobOffer_id);
//        candidacy.setJob_offer(job);
//        return iCandidacyService.addCandidate(candidacy);
//    }
//    @GetMapping("/findOneCandidacy/{candidacy_id}")
//    public Candidacy findOneActivity(@PathVariable("candidacy_id") Long candidacy_id) {
//        return iCandidacyService.findOneCandidacy(candidacy_id);
//    }
    @PutMapping ("updateCandidacy")
    public Candidacy updateCandidacy (@RequestBody Candidacy Candidacy){
        return iCandidacyService.addOrUpdateCandidacy(Candidacy);
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
    @GetMapping("/getCandidaciesByJobOfferId")
    public List<Candidacy> getCandidaciesByJobOfferId(@RequestParam Long jobOfferId) {
        return iCandidacyService.getCandidaciesByJobOfferId(jobOfferId);
    }

}
