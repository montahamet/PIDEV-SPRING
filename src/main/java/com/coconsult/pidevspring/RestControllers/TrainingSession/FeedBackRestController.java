package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Entities.FeedBack;
import com.coconsult.pidevspring.Services.TrainingSession.IFeedBackService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/FeedBack-TrainingSession")
public class FeedBackRestController {
    IFeedBackService iFeedBackService;

    @GetMapping("/findOneFeedBack")
    public FeedBack findOneFeedBack(@PathParam("feedback_id") Long feedback_id){
        return iFeedBackService.findOneFeedBack(feedback_id);
    }
    @GetMapping("/findAllFeedBacks")
    public List<FeedBack> findAllFeedBack() {
        return  iFeedBackService.findAllFeedBack();
    }
//    @PostMapping("/addFeedBack")
//    public  FeedBack addFeedBack(@RequestBody FeedBack feedBack) {
//        return iFeedBackService.addFeedback(feedBack);
//    }
    @PutMapping("/UpdateFeedBack")
    public  FeedBack UpdateFeedBack(@RequestBody FeedBack feedBack) {
        return iFeedBackService.UpdateFeedBack(feedBack);
    }
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<FeedBack>> getAllFeedbacksByEventId(@PathVariable Long eventId) {
        List<FeedBack> feedbacks = iFeedBackService.findAllFeedbacksForEvent(eventId);
        return ResponseEntity.ok(feedbacks);
    }
    @PostMapping("/{userId}/addWithNote/{eventId}")
    public ResponseEntity<?> addFeedbackWithNote(@PathVariable Long userId,
                                                 @PathVariable Long eventId,
                                                 @RequestParam String description,
                                                 @RequestParam int note) {
        try {
            FeedBack savedFeedback = iFeedBackService.addFeedbackWithNoteAndSentiment(userId, eventId, description, note);
            return ResponseEntity.ok(savedFeedback);
        } catch (RuntimeException e) {
            // Handling cases where the user or event does not exist or other business logic failures
            return ResponseEntity.badRequest().body("Error adding feedback: " + e.getMessage());
        } catch (Exception e) {
            // Generic exception handler for unexpected errors
            return ResponseEntity.internalServerError().body("Server error while processing the request: " + e.getMessage());
        }
    }
    @PostMapping("/feedback/add/{eventId}")
    public ResponseEntity<?> addFeedback(@PathVariable Long eventId, @RequestBody FeedBack feedback) {
        try {
            // Ajouter la note à l'objet Feedback
            feedback.setNote(feedback.getNote());

            // Enregistrer le feedback avec l'ID de l'événement
            FeedBack savedFeedback = iFeedBackService.addFeedback(eventId, feedback);

            return ResponseEntity.ok(savedFeedback);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding feedback: " + e.getMessage());
        }
    }
    @GetMapping("/event/{eventId}/averageRating")
    public ResponseEntity<Double> getEventAverageRating(@PathVariable Long eventId) {
        try {
            Double averageRating = iFeedBackService.getAverageRatingForEvent(eventId);
            if(averageRating == null || Double.isNaN(averageRating)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(averageRating);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    @GetMapping("/events/withRatings")
    public ResponseEntity<List<Event>> getEventsWithAverageRatings() {
        try {
            List<Event> eventsWithRatings = iFeedBackService.getEventsWithAverageRatings();
            if(eventsWithRatings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(eventsWithRatings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }



    @DeleteMapping("/deleteFeedBack")
    public void  deleteFeedBack(@PathParam("feedback_id")  Long feedback_id){
        iFeedBackService.deleteFeedBackById(feedback_id);
    }

}
