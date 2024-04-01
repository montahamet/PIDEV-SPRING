package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import com.coconsult.pidevspring.DAO.Repository.HR.JobOfferRepository;
import lombok.AllArgsConstructor;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobOfferService implements IJobOfferService {
    @Autowired
    JobOfferRepository jobOfferRepository;
    @Override
    public JobOffer addJobOffer(JobOffer JobOffer) {
        return jobOfferRepository.save(JobOffer);
    }
    @Override
    public JobOffer updateJobOffer(JobOffer updatedJobOffer) {
        // Vérifier si l'offre d'emploi existe avant de la mettre à jour
        Optional<JobOffer> existingJobOfferOptional = jobOfferRepository.findById(updatedJobOffer.getJobOffer_id());
        if (existingJobOfferOptional.isPresent()) {
            JobOffer existingJobOffer = existingJobOfferOptional.get();
            // Mettre à jour les champs de l'offre d'emploi existante avec ceux de l'offre d'emploi mise à jour
            existingJobOffer.setTitleJobOffer(updatedJobOffer.getTitleJobOffer());
            existingJobOffer.setJobLocation(updatedJobOffer.getJobLocation());
            existingJobOffer.setApplicationDeadLine(updatedJobOffer.getApplicationDeadLine());
            existingJobOffer.setExperience(updatedJobOffer.getExperience());
            existingJobOffer.setDescription(updatedJobOffer.getDescription());
            existingJobOffer.setRequiredSkills(updatedJobOffer.getRequiredSkills());
            existingJobOffer.setVacancy(updatedJobOffer.getVacancy());
            existingJobOffer.setMinsalary(updatedJobOffer.getMinsalary());
            existingJobOffer.setMaxsalary(updatedJobOffer.getMaxsalary());
            existingJobOffer.setJobNature(updatedJobOffer.getJobNature());
            existingJobOffer.setJobCategory(updatedJobOffer.getJobCategory());
            // Enregistrer les modifications dans la base de données
            return jobOfferRepository.save(existingJobOffer);
        } else {
            throw new OpenApiResourceNotFoundException("Job offer not found with id " + updatedJobOffer.getJobOffer_id());
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
    public List<Candidacy> getCandidaciesByJobOfferId(Long jobOfferId) {
        Optional<JobOffer> jobOfferOptional = jobOfferRepository.findById(jobOfferId);
        if (jobOfferOptional.isPresent()) {
            JobOffer jobOffer = jobOfferOptional.get();
            return jobOffer.getCandidacies();
        } else {
            throw new OpenApiResourceNotFoundException("Job offer not found with ID: " + jobOfferId);
        }
    }




    //    @Override
//    public JobOffer findJobOfferById(long id) {
//        return jobOfferRepository.findById(id).get();
//    }


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
//    @Override
//    public boolean hasRelatedCandidacies(Long jobId) {
//        return jobOfferRepository.existsByJobOfferId(jobId);
//    }
//    @Override
//    public JobOffer findOneJobOffer(Long jobId) {
//        return jobOfferRepository.findById(jobId).get();
//    }
    // Function to retrieve all candidacies related to a job offer ID
//    public Set<Candidacy> findAllCandidaciesByJobOfferId(Long jobOfferId) {
//        JobOffer jobOffer = jobOfferRepository.findById(jobOfferId).orElse(null);
//        if (jobOffer != null) {
//            return jobOffer.getCandidacys();
//        }
//        return null;
//    }
//    public Set<Candidacy> findAllCandidaciesByJobOfferId(Long jobOfferId) {
//        Optional<JobOffer> optionalJobOffer = jobOfferRepository.findByIdWithCandidacies(jobOfferId);
//        if (optionalJobOffer.isPresent()) {
//            JobOffer jobOffer = optionalJobOffer.get();
//            return jobOffer.getCandidacys();
//        }
//        return null;
//    }
}
