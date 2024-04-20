package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Entities.JobOffer;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IJobOfferService {
    JobOffer addJobOffer(JobOffer JobOffer);
//    JobOffer updateJobOffer(JobOffer updatedJobOffer);
 JobOffer updateJobOffer(JobOffer JobOffer);

    List<JobOffer> addAllJobOffers(List<JobOffer> JobOffers);

    List<JobOffer> findAllJobOffers();
    JobOffer findById(long id);
    void deleteJobOffer(JobOffer jobOffer);
    void deleteJobOfferById(long id);
    List<JobOffer> findByTitleJobOffer(String titleJobOffer);
    List<JobOffer> findByRequiredSkills(String requiredSkills);
    List<Candidacy> getCandidaciesByJobOfferId(Long jobOfferId);


//    boolean hasRelatedCandidacies(Long JobId);
    List<Map<String, Object>> getJobOfferStatistics() ;


    }
