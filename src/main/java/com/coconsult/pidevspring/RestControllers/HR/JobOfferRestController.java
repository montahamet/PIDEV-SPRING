package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import com.coconsult.pidevspring.Services.HR.IJobOfferService;
import lombok.AllArgsConstructor;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
@RequestMapping("/JobOffer")
public class JobOfferRestController {
    IJobOfferService iJobOfferService;

    @PostMapping("addJobOffer")
    public JobOffer addJobOffer (@RequestBody JobOffer JobOffer){
            return iJobOfferService.addJobOffer(JobOffer);
    }
//    @PutMapping ("updateJobOffer")
//    public JobOffer updateJobOffer (@RequestBody JobOffer JobOffer){
//        return iJobOfferService.updateJobOffer(JobOffer);
//    }
@PutMapping("/{jobOfferId}")
public ResponseEntity<JobOffer> updateJobOffer(@PathVariable(value = "jobOfferId") Long jobOfferId, @RequestBody JobOffer updatedJobOffer) {
    JobOffer jobOffer = iJobOfferService.findById(jobOfferId);

    if (jobOffer != null) {
        jobOffer.setTitleJobOffer(updatedJobOffer.getTitleJobOffer());
        jobOffer.setJobLocation(updatedJobOffer.getJobLocation());
        jobOffer.setApplicationDeadLine(updatedJobOffer.getApplicationDeadLine());
        jobOffer.setExperience(updatedJobOffer.getExperience());
        jobOffer.setDescription(updatedJobOffer.getDescription());
        jobOffer.setRequiredSkills(updatedJobOffer.getRequiredSkills());
        jobOffer.setVacancy(updatedJobOffer.getVacancy());
        jobOffer.setMinsalary(updatedJobOffer.getMinsalary());
        jobOffer.setMaxsalary(updatedJobOffer.getMaxsalary());
        jobOffer.setJobNature(updatedJobOffer.getJobNature());
        jobOffer.setJobCategory(updatedJobOffer.getJobCategory());

        JobOffer updatedJobOfferEntity = iJobOfferService.updateJobOffer(jobOffer);
        return ResponseEntity.ok(updatedJobOfferEntity);
    } else {
        throw new OpenApiResourceNotFoundException("Job offer not found for this id :: " + jobOfferId);
    }
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
//    @GetMapping("/{jobOfferId}/candidacies")
//    public Set<Candidacy> findAllCandidaciesByJobOfferId(@PathVariable Long jobOfferId) {
//        return iJobOfferService.findAllCandidaciesByJobOfferId(jobOfferId);
//    }


    @GetMapping("/{jobOfferId}/candidacies")
    public ResponseEntity<List<Candidacy>> getCandidaciesByJobOfferId(@PathVariable Long jobOfferId) {
        try {
            List<Candidacy> candidacies = iJobOfferService.getCandidaciesByJobOfferId(jobOfferId);
            return ResponseEntity.ok(candidacies);
        } catch (OpenApiResourceNotFoundException e) {
            // Handle the case when job offer is not found
            return ResponseEntity.notFound().build();
        }
    }
}
