package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Entities.Interview;
import com.coconsult.pidevspring.DAO.Entities.JobOffer;
import com.coconsult.pidevspring.DAO.Repository.HR.CandidacyRepository;
import com.coconsult.pidevspring.DAO.Repository.HR.InterviewRepository;
import com.coconsult.pidevspring.Services.HR.EmailInterviewService;
import com.coconsult.pidevspring.Services.HR.EmailQuizService;
import com.coconsult.pidevspring.Services.HR.IInterviewService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
@RequestMapping("/Interview")
public class InterviewRestController {
    IInterviewService iInterviewService;
    CandidacyRepository candidacyRepository;
    private final InterviewRepository interviewRepository;
    EmailInterviewService emailInterviewService;
    EmailQuizService emailQuizService;
    @PostMapping("addInterview/{candidacyId}/{id}")
    public Interview addInterview(@PathVariable Long candidacyId,@PathVariable Long id, @RequestBody Interview interview) {
        Candidacy candidacy = new Candidacy();
        candidacy = candidacyRepository.findById(candidacyId).get();
            // Set the candidacy_id on the Interview object
            interview.setCandidacy(candidacy);
        // Set candidateName and email based on Candidacy details
        interview.setCandidateName(candidacy.getCandidateName());
        interview.setEmail(candidacy.getEmail());
        Interview savedInterview = iInterviewService.addInterview(interview,id);
        scheduleEmailTask(savedInterview);
        return (savedInterview);
    }

@PutMapping("updateInterview/{interviewId}")
public ResponseEntity<Interview> updateInterview(@PathVariable Long interviewId, @RequestBody Interview interviewDetails) {
    try {
        Interview updatedInterview = iInterviewService.updateInterview(interviewId, interviewDetails);
        return ResponseEntity.ok(updatedInterview);
    } catch (RuntimeException e) {
        // Handle the case where the interview does not exist
        return ResponseEntity.notFound().build();
    }
}
    @GetMapping("/{interviewId}")
    public ResponseEntity<Interview> getInterviewById(@PathVariable Long interviewId) {
        Interview interview = iInterviewService.getInterviewById(interviewId);
        return ResponseEntity.ok(interview);
    }
    @PutMapping("passed/{interview_id}")
    public Interview updateInterviewPassed(@PathVariable("interview_id") Long interview_id, @RequestParam("passed") Boolean passed) {
        return iInterviewService.updateInterviewPassed(interview_id, passed);
    }


    @GetMapping("/success-rate")
        public ResponseEntity<Map<String, Double>> getSuccessRate() {
            Map<String, Double> successRates = iInterviewService.calculateSuccessRate();
            return ResponseEntity.ok(successRates);
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

    @PostMapping("/addInterviewByCandidacyId/{candidacyId}")
    public ResponseEntity<?> addInterviewByCandidacyId(@PathVariable Long candidacyId, @RequestBody Interview interview) {
        try {
            Interview savedInterview = iInterviewService.addInterviewByCandidacyId(candidacyId, interview);
            return ResponseEntity.ok(savedInterview);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/findInterviewsByCandidacyId/{candidacyId}")
    public ResponseEntity<List<Interview>> findInterviewsByCandidacyId(@PathVariable Long candidacyId) {
        List<Interview> interviews = iInterviewService.findInterviewsByCandidacyId(candidacyId);
        if (!interviews.isEmpty()) {
            return ResponseEntity.ok(interviews); // Return interviews if found
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if interviews not found
        }
    }
    @GetMapping("findAllInterviewsWithCandidateNames")
    public ResponseEntity<List<Interview>> findAllInterviewsWithCandidateNames() {
        List<Interview> interviews = iInterviewService.findAllInterviewsWithCandidateNames();
        if (!interviews.isEmpty()) {
            return ResponseEntity.ok(interviews); // Return interviews with candidate names if found
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if interviews not found
        }
    }
    @GetMapping("findAllInterviewsWithCandidateNamesAndEmail")
    public ResponseEntity<List<Interview>> findAllInterviewsWithCandidateNamesAndEmail() {
        List<Interview> interviews = iInterviewService.findAllInterviewsWithCandidateNamesAndEmail();
        if (!interviews.isEmpty()) {
            return ResponseEntity.ok(interviews); // Return interviews with candidate names and emails if found
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if interviews not found
        }
    }
    private void scheduleEmailTask(Interview interview) {
        // Get the interview date and time
        LocalDateTime interviewDateTime = interview.getDateInterview();

        // Calculate the date and time 5 minutes before the interview
        LocalDateTime quizReminderDateTime = interviewDateTime.minusMinutes(1);
//        LocalDateTime reminderDateTime = interviewDateTime.minusDays(4);

        // Schedule task to send email at interviewDateTime
        scheduleTask(() -> {
            String candidateEmail = interview.getEmail();
            String emailContent = "Your interview is scheduled for " + interviewDateTime.toString();
            emailInterviewService.send(candidateEmail, emailContent);
        }, interviewDateTime);
        scheduleTask(() -> {
            String candidateEmail = interview.getEmail();
            String emailContent = "Dear Candidate,\n\nYour interview is scheduled for "
                    + interviewDateTime.toString() + ". Please take the quiz using the following link: " ;
            emailQuizService.send(candidateEmail, emailContent);
        }, quizReminderDateTime);
    }

    // Method to schedule task at a specific date and time
    private void scheduleTask(Runnable task, LocalDateTime executionTime) {
        task.run();
    }


}
