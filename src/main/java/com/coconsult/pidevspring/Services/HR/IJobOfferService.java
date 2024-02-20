package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.JobOffer;

import java.util.List;

public interface IJobOfferService {
    JobOffer addJobOffer(JobOffer JobOffer);
    List<JobOffer> addAllJobOffers(List<JobOffer> JobOffers);
    JobOffer updateJobOffer(JobOffer jobOffer);
    List<JobOffer> findAllJobOffers();
    JobOffer findJobOfferById(long id);
    void deleteJobOffer(JobOffer jobOffer);
    void deleteJobOfferById(long id);
    List<JobOffer> findByTitleJobOffer(String titleJobOffer);
    List<JobOffer> findByRequiredSkills(String requiredSkills);
}
