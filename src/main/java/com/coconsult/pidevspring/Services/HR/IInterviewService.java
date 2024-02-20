package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Interview;

import java.util.List;

public interface IInterviewService {
    Interview addInterview(Interview interview);
    List<Interview> addAllInterviews(List<Interview> interviews);
    Interview updateInterview(Interview interview);
    List<Interview> findAllInterviews();
    Interview findInterviewById(long id);
    void deleteInterview(Interview interview);
    void deleteInterviewById(long id);
}
