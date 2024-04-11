package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Entities.Interview;
import com.coconsult.pidevspring.DAO.Repository.HR.CandidacyRepository;
import com.coconsult.pidevspring.DAO.Repository.HR.InterviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InterviewService implements IInterviewService{
    @Autowired
    InterviewRepository interviewRepository;
    CandidacyRepository candidacyRepository;

    @Override
    public Interview addOrUpdateInterview(Interview interview) {
        return interviewRepository.save(interview);
    }
    @Override
    public Interview addInterview(Interview interview) {
        return interviewRepository.save(interview);
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


}
