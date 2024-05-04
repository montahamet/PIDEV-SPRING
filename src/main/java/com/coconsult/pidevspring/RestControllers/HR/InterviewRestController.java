package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.DAO.Entities.Interview;
import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import com.coconsult.pidevspring.Services.HR.IInterviewService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
@RequestMapping("/Interview")
public class InterviewRestController {
    IInterviewService iInterviewService;

    @PostMapping("/addInterview/{candidacyId}")
    public ResponseEntity<?> addInterview(@PathVariable Long candidacyId, @RequestBody Interview interview) {
        try {
            // Set the candidacy_id on the Interview object
            interview.getCandidacy().setCandidacy_id(candidacyId);
            // Save the Interview entity
            Interview savedInterview = iInterviewService.addOrUpdateInterview(interview, candidacyId);
            return ResponseEntity.ok(savedInterview);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
//    @PutMapping ("updateInterview")
//    public Interview updateInterview (@RequestBody Interview Interview){
//        return iInterviewService.addOrUpdateInterview(Interview);
//    }

        @GetMapping("/success-rate")
        public ResponseEntity<Map<String, Double>> getSuccessRate() {
            Map<String, Double> successRates = iInterviewService.calculateSuccessRate();
            return ResponseEntity.ok(successRates);
        }


    @PostMapping("addAllInterviews")
    public List<Interview> addAllInterviews(@RequestBody List<Interview> Interviews){
        return iInterviewService.addAllInterviews(Interviews);
    }

    @GetMapping("getInterview/{id}")
    public Interview getInterview(@PathVariable("id") long id){
        return iInterviewService.findInterviewById(id);
    }
    @GetMapping("findAllInterviews")
    public List<Interview> findAllInterviews() {
        return iInterviewService.findAllInterviews();
    }

    @DeleteMapping("deleteInterview")
    public void delete(@RequestBody Interview Interview){
        iInterviewService.deleteInterview(Interview);
    }

    @DeleteMapping("deleteInterviewById/{id}")
    public void deleteInterviewById(@PathVariable("id") long id){
        iInterviewService.deleteInterviewById(id);
    }
    @GetMapping("/findByDateInterview")//a verifier
    public List<Interview> findByDateInterview(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateInterview) {
        return iInterviewService.findByDateInterview(dateInterview);
    }

    @PostMapping("/addInterviewByCandidacyId/{candidacyId}")
    public ResponseEntity<?> addInterviewByCandidacyId(@PathVariable Long candidacyId, @RequestBody Interview interview) {
        try {
            Interview savedInterview = iInterviewService.addInterviewByCandidacyId(candidacyId, interview);
            return ResponseEntity.ok(savedInterview);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/findInterviewsByCandidacyId/{candidacyId}")
    public ResponseEntity<List<Interview>> findInterviewsByCandidacyId(@PathVariable Long candidacyId) {
        List<Interview> interviews = iInterviewService.findInterviewsByCandidacyId(candidacyId);
        if (!interviews.isEmpty()) {
            return ResponseEntity.ok(interviews); // Return interviews if found
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if interviews not found
        }
    }
    @GetMapping("findAllInterviewsWithCandidateNames")
    public ResponseEntity<List<Interview>> findAllInterviewsWithCandidateNames() {
        List<Interview> interviews = iInterviewService.findAllInterviewsWithCandidateNames();
        if (!interviews.isEmpty()) {
            return ResponseEntity.ok(interviews); // Return interviews with candidate names if found
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if interviews not found
        }
    }
    @GetMapping("findAllInterviewsWithCandidateNamesAndEmail")
    public ResponseEntity<List<Interview>> findAllInterviewsWithCandidateNamesAndEmail() {
        List<Interview> interviews = iInterviewService.findAllInterviewsWithCandidateNamesAndEmail();
        if (!interviews.isEmpty()) {
            return ResponseEntity.ok(interviews); // Return interviews with candidate names and emails if found
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if interviews not found
        }
    }


}
