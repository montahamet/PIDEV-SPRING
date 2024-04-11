package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Activity;
import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.EventRepository;
import com.coconsult.pidevspring.Services.TrainingSession.IEventService;
import com.coconsult.pidevspring.Services.TrainingSession.IFeedBackService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/Event-TrainingSession")

public class EventRestController {
    IEventService iEventService;
    EventRepository eventRepository;
    IFeedBackService iFeedBackService;
    @GetMapping("/{eventId}/hasRelatedActivities")
    public ResponseEntity<Boolean> checkEventRelatedActivities(@PathVariable Long eventId) {
        boolean hasRelated = iEventService.hasRelatedActivities(eventId);
        return ResponseEntity.ok(hasRelated);
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
    public  Event addEvent(@RequestBody Event event) {
        return iEventService.addEvent(event);
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
