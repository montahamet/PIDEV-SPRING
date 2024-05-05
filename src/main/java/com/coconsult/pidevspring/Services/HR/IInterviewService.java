package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Interview;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IInterviewService {
     Interview updateInterview(Long interviewId, Interview interviewDetails) ;
     Interview getInterviewById(Long interviewId) ;


 Interview updateInterviewPassed(Long interview_id  , Boolean passed);

    Interview addInterview(Interview interview, Long id);

    List<Interview> addAllInterviews(List<Interview> interviews);
    List<Interview> findAllInterviews();
    Interview findInterviewById(long id);
    void deleteInterview(Interview interview);
    void deleteInterviewById(long id);
    List<Interview> findByDateInterview(LocalDateTime dateInterview);
     Interview addInterviewByCandidacyId(Long candidacyId, @RequestBody Interview interview);
     Map<String, Double> calculateSuccessRate() ;
     List<Interview> findInterviewsByCandidacyId(Long candidacyId);
     List<Interview> findAllInterviewsWithCandidateNames();
     List<Interview> findAllInterviewsWithCandidateNamesAndEmail();




    }
