package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Entities.Interview;
import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.HR.CandidacyRepository;
import com.coconsult.pidevspring.DAO.Repository.HR.InterviewRepository;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InterviewService implements IInterviewService{
//    @Autowired
    InterviewRepository interviewRepository;
    CandidacyRepository candidacyRepository;
    private UserRepository userRepository ;



    @Override
    public Interview addInterview(Interview interview,Long id) {
        User u = new User();
        u = userRepository.findById(id).get();
        interview.setUser(u);
        return interviewRepository.save(interview);
    }
    public Interview updateInterview(Long interviewId, Interview interviewDetails) {
        // Find the existing interview by ID
        Optional<Interview> existingInterview = interviewRepository.findById(interviewId);

        // Check if the interview exists
        if (existingInterview.isPresent()) {
            // Update the fields of the existing interview with the details provided
            Interview existingInterviewEntity = existingInterview.get();
            existingInterviewEntity.setDateInterview(interviewDetails.getDateInterview());
            existingInterviewEntity.setStatusInterview(interviewDetails.getStatusInterview());
            existingInterviewEntity.setPassed(interviewDetails.isPassed());

            // Save the updated interview back to the database
            return interviewRepository.save(existingInterviewEntity);
        } else {
            // Handle the case where the interview does not exist
            // This could be throwing an exception, logging a message, etc.
            throw new RuntimeException("Interview not found with id " + interviewId);
        }
    }
    public Interview getInterviewById(Long interviewId) {
        return interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found with id " + interviewId));
    }

public Interview updateInterviewPassed(Long interview_id, Boolean passed) {
    Interview interview = interviewRepository.findById(interview_id).orElse(null);
    if (interview != null) {
        interview.setPassed(passed);
        return interviewRepository.save(interview);
    }
    return null; // Or throw an exception if interview not found
}

    @Override
    public List<Interview> addAllInterviews(List<Interview> interviews) {
        return interviewRepository.saveAll(interviews);
    }

    @Override
    public List<Interview> findAllInterviews() {
        return interviewRepository.findAll();
    }

    @Override
    public Interview findInterviewById(long id) {
        return interviewRepository.findById(id).get();
    }

    @Override
    public void deleteInterview(Interview interview) {
        interviewRepository.delete(interview);

    }

    @Override
    public void deleteInterviewById(long id) {
        interviewRepository.deleteById(id);

    }
    public List<Interview> findInterviewsByCandidacyId(Long candidacyId) {
        return interviewRepository.findByCandidacyId(candidacyId);
    }


    @Override
    public List<Interview> findByDateInterview(LocalDateTime dateInterview) {
        return interviewRepository.findByDateInterview(dateInterview);
    }
    @Override
    public Interview addInterviewByCandidacyId(Long candidacyId, @RequestBody Interview interview) {
        Optional<Candidacy> optionalCandidacy = candidacyRepository.findById(candidacyId);
        if (optionalCandidacy.isPresent()) {
            Candidacy candidacy = optionalCandidacy.get();
            interview.setCandidacy(candidacy);
            return interviewRepository.save(interview);
        } else {
            throw new IllegalArgumentException("Candidacy with ID " + candidacyId + " not found.");
        }
    }
    public Map<String, Double> calculateSuccessRate() {
        Map<String, Double> successRates = new HashMap<>();
        List<Interview> interviews = interviewRepository.findAll();
        if (interviews.isEmpty()) {
            successRates.put("successfulPercentage", 0.0);
            successRates.put("unsuccessfulPercentage", 0.0);
            return successRates; // If there are no interviews, success rates are both 0%
        }

        long totalInterviews = interviews.size();
        long successfulInterviews = interviews.stream()
                .filter(Interview::getPassed) // Filter interviews that have passed
                .count();

        long unsuccessfulInterviews = totalInterviews - successfulInterviews;

        double successfulPercentage = (double) successfulInterviews / totalInterviews * 100.0;
        double unsuccessfulPercentage = (double) unsuccessfulInterviews / totalInterviews * 100.0;

        successRates.put("successful Interviews", successfulPercentage);
        successRates.put("unsuccessful Interviews", unsuccessfulPercentage);

        return successRates;
    }
    public List<Interview> findAllInterviewsWithCandidateNames() {
        List<Interview> interviews = interviewRepository.findAll(); // Fetch interviews
        for (Interview interview : interviews) {
            // Fetch candidacy information for each interview
            Long candidacyId = interview.getCandidacy().getCandidacy_id();
            Optional<Candidacy> optionalCandidacy = candidacyRepository.findById(candidacyId);
            optionalCandidacy.ifPresent(candidacy -> {
                // Set candidate name in the interview object
                interview.setCandidateName(candidacy.getCandidateName());
            });
        }
        return interviews;
    }
    public List<Interview> findAllInterviewsWithCandidateNamesAndEmail() {
        List<Interview> interviews = interviewRepository.findAll(); // Fetch interviews
        for (Interview interview : interviews) {
            // Fetch candidacy information for each interview
            Long candidacyId = interview.getCandidacy().getCandidacy_id();
            Optional<Candidacy> optionalCandidacy = candidacyRepository.findById(candidacyId);
            optionalCandidacy.ifPresent(candidacy -> {
                // Set candidate name and email in the interview object
                interview.setCandidateName(candidacy.getCandidateName());
                interview.setEmail(candidacy.getEmail());
            });
        }
        return interviews;
    }



}
