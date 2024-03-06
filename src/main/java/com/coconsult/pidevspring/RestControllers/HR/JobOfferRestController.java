package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import com.coconsult.pidevspring.Services.HR.IJobOfferService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RequestMapping("/JobOffer")
public class JobOfferRestController {
    IJobOfferService iJobOfferService;

    @PostMapping("addJobOffer")
    public JobOffer addJobOffer (@RequestBody JobOffer JobOffer){
        return iJobOfferService.addOrUpdateJobOffer(JobOffer);
    }
    @PutMapping ("updateJobOffer")
    public JobOffer updateJobOffer (@RequestBody JobOffer JobOffer){
        return iJobOfferService.addOrUpdateJobOffer(JobOffer);
    }


    @PostMapping("addAllJobOffers")
    public List<JobOffer> addAllJobOffers(@RequestBody List<JobOffer> JobOffers){
        return iJobOfferService.addAllJobOffers(JobOffers);
    }

    @GetMapping("getJobOffer/{id}")
    public JobOffer getJobOffer(@PathVariable("id") long id){
        return iJobOfferService.findJobOfferById(id);
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
}
