package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Repository.HR.CandidacyRepository;
import com.coconsult.pidevspring.Services.HR.ImarkService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.coconsult.pidevspring.DAO.Entities.mark;


@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
@RequestMapping( "/Mark")
public class markRestController {
    @Autowired
    ImarkService imarkService;

    CandidacyRepository candidacyRepository;

    @PostMapping("addMark/{candidacyId}")
    public mark addMark (@RequestBody mark mark, @PathVariable Long candidacyId){
        Candidacy candidacy = candidacyRepository.findById(candidacyId).orElse(null); // Use orElse(null) to handle the case when the candidacy is not found
        if (candidacy != null) {
            // Set the candidacy_id on the Interview object
            mark.setCandidacy(candidacy);
            mark.setCandidateName(candidacy.getCandidateName());
            return imarkService.addMark(mark);
        } else {
            // Handle the case when candidacy is not found
            return null; // or throw an exception
        }
    }

//    @GetMapping("/getCandidateName/{candidacyId}")
//    public ResponseEntity<String> getCandidateName(@PathVariable Long candidacyId) {
//        Candidacy candidacy = candidacyRepository.findById(candidacyId).orElse(null);
//        if (candidacy != null) {
//            return ResponseEntity.ok(candidacy.getCandidateName());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


}
