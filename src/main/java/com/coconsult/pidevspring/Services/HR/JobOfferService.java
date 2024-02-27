package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import com.coconsult.pidevspring.DAO.Repository.HR.JobOfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobOfferService implements IJobOfferService {
    JobOfferRepository jobOfferRepository;
    @Override
    public JobOffer addJobOffer(JobOffer JobOffer) {
        return jobOfferRepository.save(JobOffer);
    }

    @Override
    public List<JobOffer> addAllJobOffers(List<JobOffer> JobOffers) {
        return jobOfferRepository.saveAll(JobOffers);
    }

    @Override
    public JobOffer updateJobOffer(JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }

    @Override
    public List<JobOffer> findAllJobOffers() {
        return jobOfferRepository.findAll();
    }

    @Override
    public JobOffer findJobOfferById(long id) {
        return jobOfferRepository.findById(id).get();
    }

    @Override
    public void deleteJobOffer(JobOffer jobOffer) {
        jobOfferRepository.delete(jobOffer);

    }

    @Override
    public void deleteJobOfferById(long id) {
        jobOfferRepository.deleteById(id);

    }


    public List<JobOffer> findByTitleJobOffer(String titleJobOffer) {
        return jobOfferRepository.findByTitleJobOffer(titleJobOffer);
    }
    @Override
    public List<JobOffer> findByRequiredSkills(String requiredSkills) {
        return jobOfferRepository.findByRequiredSkills(requiredSkills);
    }
}
