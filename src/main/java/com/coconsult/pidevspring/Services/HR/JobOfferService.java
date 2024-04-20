package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import com.coconsult.pidevspring.DAO.Repository.HR.CandidacyRepository;
import com.coconsult.pidevspring.DAO.Repository.HR.JobOfferRepository;
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

    @Override
    public JobOffer addJobOffer(JobOffer JobOffer) {
        return jobOfferRepository.save(JobOffer);
    }
    public JobOffer updateJobOffer(JobOffer JobOffer) {
        return jobOfferRepository.save(JobOffer);
    }

    //    @Override
//    public JobOffer updateJobOffer(JobOffer updatedJobOffer) {
//        // Vérifier si l'offre d'emploi existe avant de la mettre à jour
//        Optional<JobOffer> existingJobOfferOptional = jobOfferRepository.findById(updatedJobOffer.getJobOffer_id());
//        if (existingJobOfferOptional.isPresent()) {
//            JobOffer existingJobOffer = existingJobOfferOptional.get();
//            // Mettre à jour les champs de l'offre d'emploi existante avec ceux de l'offre d'emploi mise à jour
//            existingJobOffer.setTitleJobOffer(updatedJobOffer.getTitleJobOffer());
//            existingJobOffer.setJobLocation(updatedJobOffer.getJobLocation());
//            existingJobOffer.setApplicationDeadLine(updatedJobOffer.getApplicationDeadLine());
//            existingJobOffer.setExperience(updatedJobOffer.getExperience());
//            existingJobOffer.setDescription(updatedJobOffer.getDescription());
//            existingJobOffer.setRequiredSkills(updatedJobOffer.getRequiredSkills());
//            existingJobOffer.setVacancy(updatedJobOffer.getVacancy());
//            existingJobOffer.setMinsalary(updatedJobOffer.getMinsalary());
//            existingJobOffer.setMaxsalary(updatedJobOffer.getMaxsalary());
////            existingJobOffer.setJobNature(updatedJobOffer.getJobNature());
////            existingJobOffer.setJobCategory(updatedJobOffer.getJobCategory());
//            // Enregistrer les modifications dans la base de données
//            return jobOfferRepository.save(existingJobOffer);
//        } else {
//            throw new OpenApiResourceNotFoundException("Job offer not found with id " + updatedJobOffer.getJobOffer_id());
//        }
//    }
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
