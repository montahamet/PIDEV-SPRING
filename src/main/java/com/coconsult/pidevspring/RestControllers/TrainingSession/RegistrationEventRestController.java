package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.RegistrationEvent;
import com.coconsult.pidevspring.Services.TrainingSession.IRegistrationEventService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/RegistrationEvent-TrainingSession")
public class RegistrationEventRestController {
    IRegistrationEventService iRegistrationEventService;

    @GetMapping("/findOneRegistrationEvent")
    public RegistrationEvent findOneRegistrationEvent(@PathParam("registrationE_id") Long registrationE_id){
        return iRegistrationEventService.findOneRegistrationEvent(registrationE_id);
    }
    @PostMapping("/register")
    public ResponseEntity<RegistrationEvent> registerForEvent(@RequestParam Long eventId, @RequestParam Long userId) {
        RegistrationEvent registration = iRegistrationEventService.registerForEvent(eventId, userId);
        return ResponseEntity.ok(registration);
    }
    @GetMapping("/findAllRegistrationEvent")
    public List<RegistrationEvent> findAllRegistrationEvent() {
        return  iRegistrationEventService.findAllRegistrationEvent();
    }
    @PostMapping("/addRegistrationEvent/{eventId}/{userId}")
    public ResponseEntity<?> addRegistrationEvent(@PathVariable Long eventId, @PathVariable Long userId) {
        try {
            RegistrationEvent registrationEvent = iRegistrationEventService.addRegistrationEvent(eventId, userId);
            return ResponseEntity.ok().build(); // Return 200 OK with an empty body
        } catch (OpenApiResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PostMapping("/UpdateRegistrationEvent")
    public  RegistrationEvent UpdateRegistrationEvent(@RequestBody RegistrationEvent registrationEvent) {
        return iRegistrationEventService.UpdateRegistrationEvent(registrationEvent);
    }

    @DeleteMapping("/deleteRegistrationEvent")
    public void  deleteRegistrationEvent(@PathParam("registrationE_id") Long registrationE_id){
        iRegistrationEventService.deleteRegistrationEventById(registrationE_id);
    }

}
