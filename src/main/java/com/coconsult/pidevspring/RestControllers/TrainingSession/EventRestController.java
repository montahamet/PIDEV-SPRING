package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Activity;
import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.EventRepository;
import com.coconsult.pidevspring.Services.TrainingSession.IActivityService;
import com.coconsult.pidevspring.Services.TrainingSession.IEmailEventService;
import com.coconsult.pidevspring.Services.TrainingSession.IEventService;
import com.coconsult.pidevspring.Services.TrainingSession.IFeedBackService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@AllArgsConstructor
<<<<<<< HEAD
=======

//@CrossOrigin("*")
>>>>>>> 340bb1611de4d28d73c923a57941f8b1cd8d1183
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/Event-TrainingSession")

public class EventRestController {
    IEventService iEventService;
    EventRepository eventRepository;
    IFeedBackService iFeedBackService;
    IActivityService iActivityService;
    IEmailEventService iEmailEventService;
    @GetMapping("/{eventId}/hasRelatedActivities")
    public ResponseEntity<Boolean> checkEventRelatedActivities(@PathVariable Long eventId) {
        boolean hasRelated = iEventService.hasRelatedActivities(eventId);
        return ResponseEntity.ok(hasRelated);
    }
    @GetMapping("/searchLocation")
    public ResponseEntity<String> proxySearchLocation(@RequestParam("query") String query) {
        final String url = "https://nominatim.openstreetmap.org/search?format=json&q=" + query;
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "SpringBootApp"); // Adjust this as per OpenStreetMap's requirements

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response;
    }
    @GetMapping("/getRelatedActivities/{eventId}")
    public ResponseEntity<List<Activity>> getActivitiesByEventId(@PathVariable Long eventId) {
        List<Activity> activities = iActivityService.getActivitiesByEventId(eventId); // Correctly calling on the instance
        if (activities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(activities);
    }

//    @GetMapping("/searchLocation")
//    public ResponseEntity<String> proxySearchLocation(@RequestParam("query") String query) {
//        final String url = "https://nominatim.openstreetmap.org/search?format=json&q=" + query;
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("User-Agent", "VotreApplication + http://votreDomaine.com");
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//
//        return response;
//    }


//    @GetMapping("/searchLocation")
//    public ResponseEntity<String> proxySearchLocation(@RequestParam("query") String query) {
//        final String url = "https://nominatim.openstreetmap.org/search?format=json&q=" + query;
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("User-Agent", "SpringBootApp"); // Nominatim requiert un User-Agent personnalisé
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//
//        return response;
//    }
@PostMapping("/sendConfirmation")
public void sendEmailConfirmation(@RequestParam Long userId, @RequestParam String eventName) {
    iEmailEventService.sendEventConfirmationEmail(userId, eventName);
}

    @GetMapping("/findOneEvent/{eventId}")
    public Event findOneEvent(@PathVariable("eventId") Long eventId){
        return iEventService.findOneEvent(eventId);
    }
    @GetMapping("/events")
    public Page<Event> findAllEvent(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return iEventService.findAllEvent(pageable);
    }
    @PostMapping("/addEvent")
    public ResponseEntity<?> addEvent(@Valid @RequestBody Event event, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Event newEvent = iEventService.addEvent(event);
        return ResponseEntity.ok(newEvent);
    }

    @PutMapping("/events/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable(value = "eventId") Long eventId, @RequestBody Event eventDetails) {
        Event updatedEvent = iEventService.UpdateEvent(eventDetails); // Utiliser la méthode appropriée du service
        return ResponseEntity.ok(updatedEvent);
    }
    @GetMapping("/upcoming")
    public List<Event> getUpcomingEvents() {
        return iEventService.getUpcomingEvents();
    }
//    public ResponseEntity<Event> updateEvent(@PathVariable(value = "eventId") Long eventId, @RequestBody Event eventDetails) {
//        Event event = eventRepository.findById(eventId)
//                .orElseThrow(() -> new OpenApiResourceNotFoundException("Event not found for this id :: " + eventId));
//
//        event.setEvent_name(eventDetails.getEvent_name());
//        event.setEvent_description(eventDetails.getEvent_description());
//        event.setEvent_date(eventDetails.getEvent_date());
//        event.setPlace(eventDetails.getPlace());
//        final Event updatedEvent = eventRepository.save(event);
//        return ResponseEntity.ok(updatedEvent);
//    }

    @DeleteMapping("/deleteEvent/{eventId}")
    public void deleteEvent(@PathVariable("eventId") Long eventId) {
        iEventService.deleteEventById(eventId);
    }
    @GetMapping("/withRatings")
    public ResponseEntity<List<Event>> getEventsWithRatings() {
        List<Event> eventsWithRatings = iFeedBackService.getEventsWithAverageRatings();
        return ResponseEntity.ok(eventsWithRatings);
    }
    @PutMapping("/{eventId}/updateAverageRating")
    public ResponseEntity<?> updateEventAverageRating(@PathVariable Long eventId) {
        try {
            iEventService.updateEventAverageRating(eventId);
            return ResponseEntity.ok().body("Average rating updated successfully for event with ID: " + eventId);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
//    @PostMapping("/{eventId}/like")
//    public ResponseEntity<Event> likeEvent(@PathVariable Long eventId) {
//        iEventService.likeEvent(eventId, userId); // Replace 'userId' with the actual user ID
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/{eventId}/dislike")
//    public ResponseEntity<Event> dislikeEvent(@PathVariable Long eventId) {
//        iEventService.dislikeEvent(eventId, userId); // Replace 'userId' with the actual user ID
//        return ResponseEntity.ok().build();
//    }



}
