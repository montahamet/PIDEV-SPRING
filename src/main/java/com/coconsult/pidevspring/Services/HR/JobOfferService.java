package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Entities.Interview;
import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.HR.CandidacyRepository;
import com.coconsult.pidevspring.DAO.Repository.HR.JobOfferRepository;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobOfferService implements IJobOfferService {
    @Autowired
    JobOfferRepository jobOfferRepository;
    private CandidacyRepository candidacyRepository;
    private UserRepository userRepository ;

    @Override
    public JobOffer addJobOffer(JobOffer JobOffer , Long id) {
        User u = new User();
        u = userRepository.findById(id).get();
        JobOffer.setUser(u);
        return jobOfferRepository.save(JobOffer);
    }
    public JobOffer getJobOfferById(Long jobOfferId) {
        return jobOfferRepository.findById(jobOfferId)
                .orElseThrow(() -> new RuntimeException("Job Offer not found with id " + jobOfferId));
    }
    public JobOffer updateJobOffer(Long jobOfferId, JobOffer jobOfferDetails) {
        // Find the existing job offer by ID
        Optional<JobOffer> existingJobOffer = jobOfferRepository.findById(jobOfferId);

        // Check if the job offer exists
        if (existingJobOffer.isPresent()) {
            // Update the fields of the existing job offer with the details provided
            JobOffer existingJobOfferEntity = existingJobOffer.get();
            existingJobOfferEntity.setTitleJobOffer(jobOfferDetails.getTitleJobOffer());
            existingJobOfferEntity.setJobLocation(jobOfferDetails.getJobLocation());
            existingJobOfferEntity.setApplicationDeadLine(jobOfferDetails.getApplicationDeadLine());
            existingJobOfferEntity.setExperience(jobOfferDetails.getExperience());
            existingJobOfferEntity.setPostedDate(jobOfferDetails.getPostedDate());
            existingJobOfferEntity.setDescription(jobOfferDetails.getDescription());
            existingJobOfferEntity.setRequiredSkills(jobOfferDetails.getRequiredSkills());
            existingJobOfferEntity.setVacancy(jobOfferDetails.getVacancy());
            existingJobOfferEntity.setMinsalary(jobOfferDetails.getMinsalary());
            existingJobOfferEntity.setMaxsalary(jobOfferDetails.getMaxsalary());
            existingJobOfferEntity.setJobNature(jobOfferDetails.getJobNature());
            existingJobOfferEntity.setJobCategory(jobOfferDetails.getJobCategory());

            // Save the updated job offer back to the database
            return jobOfferRepository.save(existingJobOfferEntity);
        } else {
            // Handle the case where the job offer does not exist
            // This could be throwing an exception, logging a message, etc.
            throw new RuntimeException("Job offer not found with id " + jobOfferId);
        }
    }

    @Override
    public List<JobOffer> addAllJobOffers(List<JobOffer> JobOffers) {
        return jobOfferRepository.saveAll(JobOffers);
    }



    @Override
    public List<JobOffer> findAllJobOffers() {
        return jobOfferRepository.findAll();
    }

    @Override
    public JobOffer findById(long id) {
        return jobOfferRepository.findById(id).orElse(null);
    }
    @Override
    public List<Candidacy> getCandidaciesByJobOfferId(Long jobOfferId) {
        Optional<JobOffer> jobOfferOptional = jobOfferRepository.findById(jobOfferId);
        if (jobOfferOptional.isPresent()) {
            JobOffer jobOffer = jobOfferOptional.get();
            return jobOffer.getCandidacies();
        } else {
            throw new OpenApiResourceNotFoundException("Job offer not found with ID: " + jobOfferId);
        }
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
    public List<Map<String, Object>> getJobOfferStatistics() {
        // Retrieve all job offers
        List<JobOffer> jobOffers = jobOfferRepository.findAll();

        // Create a list to store job offer statistics
        List<Map<String, Object>> jobOfferStatisticsList = new ArrayList<>();

        // Iterate over each job offer
        for (JobOffer jobOffer : jobOffers) {
            // Retrieve the number of candidacies associated with the current job offer
            List<Candidacy> candidacies = candidacyRepository.findByJobOfferId(jobOffer.getJobOffer_id());
            int numberOfCandidates = candidacies.size();

            // Create a map to store the statistics for the current job offer
            Map<String, Object> jobOfferStatistics = new HashMap<>();
            jobOfferStatistics.put("jobOfferTitle", jobOffer.getTitleJobOffer());
            jobOfferStatistics.put("numberOfCandidates", numberOfCandidates);

            // Add the statistics to the list
            jobOfferStatisticsList.add(jobOfferStatistics);
        }

        return jobOfferStatisticsList;
    }
}
