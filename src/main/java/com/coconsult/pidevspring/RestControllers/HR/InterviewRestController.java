package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.DAO.Entities.Interview;
import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import com.coconsult.pidevspring.Services.HR.IInterviewService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
@RequestMapping("/Interview")
public class InterviewRestController {
    IInterviewService iInterviewService;

    @PostMapping("addInterview")
    public Interview addInterview (@RequestBody Interview Interview){
        return iInterviewService.addOrUpdateInterview(Interview);
    }
    @PutMapping ("updateInterview")
    public Interview updateInterview (@RequestBody Interview Interview){
        return iInterviewService.addOrUpdateInterview(Interview);
    }




    @PostMapping("addAllInterviews")
    public List<Interview> addAllInterviews(@RequestBody List<Interview> Interviews){
        return iInterviewService.addAllInterviews(Interviews);
    }

    @GetMapping("getInterview/{id}")
    public Interview getInterview(@PathVariable("id") long id){
        return iInterviewService.findInterviewById(id);
    }
    @GetMapping("findAllInterviews")
    public List<Interview> findAllInterviews() {
        return iInterviewService.findAllInterviews();
    }

    @DeleteMapping("deleteInterview")
    public void delete(@RequestBody Interview Interview){
        iInterviewService.deleteInterview(Interview);
    }

    @DeleteMapping("deleteInterviewById/{id}")
    public void deleteInterviewById(@PathVariable("id") long id){
        iInterviewService.deleteInterviewById(id);
    }
    @GetMapping("/findByDateInterview")//a verifier
    public List<Interview> findByDateInterview(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateInterview) {
        return iInterviewService.findByDateInterview(dateInterview);
    }
}
