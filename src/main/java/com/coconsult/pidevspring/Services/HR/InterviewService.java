package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Interview;
import com.coconsult.pidevspring.DAO.Repository.HR.InterviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class InterviewService implements IInterviewService{
    InterviewRepository interviewRepository;
    @Override
    public Interview addInterview(Interview interview) {
        return interviewRepository.save(interview);
    }

    @Override
    public List<Interview> addAllInterviews(List<Interview> interviews) {
        return interviewRepository.saveAll(interviews);
    }

    @Override
    public Interview updateInterview(Interview interview) {
        return interviewRepository.save(interview);
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
}
