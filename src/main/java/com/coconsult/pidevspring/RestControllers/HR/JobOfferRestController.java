package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Entities.Interview;
import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import com.coconsult.pidevspring.Services.HR.IJobOfferService;
import lombok.AllArgsConstructor;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
@RequestMapping( "/JobOffer")
public class JobOfferRestController {
    IJobOfferService iJobOfferService;

    @PostMapping("addJobOffer/{id}")
    public JobOffer addJobOffer (@PathVariable Long id ,@RequestBody JobOffer JobOffer){
            return iJobOfferService.addJobOffer(JobOffer,id);
    }
    @PutMapping("updateJobOffer/{jobOfferId}")
    public ResponseEntity<JobOffer> updateJobOffer(@PathVariable Long jobOfferId, @RequestBody JobOffer jobOfferDetails) {
        try {
            JobOffer updatedJobOffer = iJobOfferService.updateJobOffer(jobOfferId, jobOfferDetails);
            return ResponseEntity.ok(updatedJobOffer);
        } catch (RuntimeException e) {
            // Handle the case where the job offer does not exist
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{jobOfferId}")
    public ResponseEntity<JobOffer> getJobOfferById(@PathVariable Long jobOfferId) {
        JobOffer jobOffer = iJobOfferService.getJobOfferById(jobOfferId);
        return ResponseEntity.ok(jobOffer);
    }
    @PostMapping("addAllJobOffers")
    public List<JobOffer> addAllJobOffers(@RequestBody List<JobOffer> JobOffers){
        return iJobOfferService.addAllJobOffers(JobOffers);
    }

    @GetMapping("getJobOffer/{id}")
    public JobOffer getJobOffer(@PathVariable("id") long id){
        return iJobOfferService.findById(id);
    }
    @GetMapping("findAllJobOffers")
    public List<JobOffer> findAllJobOffers() {
        return iJobOfferService.findAllJobOffers();
    }

    @DeleteMapping("deleteJobOffer")
    public void delete(@RequestBody JobOffer JobOffer){
        iJobOfferService.deleteJobOffer(JobOffer);
    }

    @DeleteMapping("deleteJobOfferById/{id}")
    public void deleteJobOfferById(@PathVariable("id") long id){
        iJobOfferService.deleteJobOfferById(id);
    }
    @GetMapping("/findByTitleJobOffer")
    public List<JobOffer> findByTitleJobOffer(@RequestParam String titleJobOffer) {
        return iJobOfferService.findByTitleJobOffer(titleJobOffer);
    }
    @GetMapping("/findByRequiredSkills")
    public List<JobOffer> findByRequiredSkills(@RequestParam String requiredSkills) {
        return iJobOfferService.findByRequiredSkills(requiredSkills);
    }

    @GetMapping("/findAllJobCandidacies")
    public ResponseEntity<List<Candidacy>> getCandidaciesByJobOfferId(@RequestParam Long jobOfferId) {
        try {
            List<Candidacy> candidacies = iJobOfferService.getCandidaciesByJobOfferId(jobOfferId);
            return ResponseEntity.ok(candidacies);
        } catch (OpenApiResourceNotFoundException e) {
            // Handle the case when job offer is not found
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getJobOfferStatistics")
    public ResponseEntity<List<Map<String, Object>>> getJobOfferStatistics() {
        try {
            List<Map<String, Object>> statistics = iJobOfferService.getJobOfferStatistics();
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            // Handle the case when an error occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
