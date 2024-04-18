package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.RegistrationEvent;
import com.coconsult.pidevspring.Services.TrainingSession.IRegistrationEventService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
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
    @PostMapping("/addRegistrationEvent")
    public  RegistrationEvent addRegistrationEvent(@RequestBody RegistrationEvent registrationEvent) {
        return iRegistrationEventService.addRegistrationEvent(registrationEvent);
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
