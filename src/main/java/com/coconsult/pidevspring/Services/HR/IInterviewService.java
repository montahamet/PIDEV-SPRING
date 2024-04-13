package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Interview;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

public interface IInterviewService {
    Interview addOrUpdateInterview(Interview interview);
    Interview addInterview(Interview interview);
    List<Interview> addAllInterviews(List<Interview> interviews);
    List<Interview> findAllInterviews();
    Interview findInterviewById(long id);
    void deleteInterview(Interview interview);
    void deleteInterviewById(long id);
    List<Interview> findByDateInterview(LocalDateTime dateInterview);
     Interview addInterviewByCandidacyId(Long candidacyId, @RequestBody Interview interview);

    }
