package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.RegistrationEvent;
import com.coconsult.pidevspring.Services.TrainingSession.IRegistrationEventService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/RegistrationEvent-TrainingSession")
public class RegistrationEventRestController {
    IRegistrationEventService iRegistrationEventService;

    @GetMapping("/findOneRegistrationEvent")
    public RegistrationEvent findOneRegistrationEvent(@PathParam("registrationE_id") Long registrationE_id){
        return iRegistrationEventService.findOneRegistrationEvent(registrationE_id);
    }
    @GetMapping("/findAllRegistrationEvent")
    public List<RegistrationEvent> findAllActivities() {
        return  iRegistrationEventService.findAllRegistrationEvent();
    }
    @PostMapping("/addRegistrationEvent")
    public  RegistrationEvent addRegistrationEvent(@RequestBody RegistrationEvent registrationEvent) {
        return iRegistrationEventService.addRegistrationEvent(registrationEvent);
    }
    @PostMapping("/updateRegistrationEvent")
    public RegistrationEvent updateRegistrationEvent(@RequestBody RegistrationEvent registrationEvent){
        return iRegistrationEventService.updateRegistrationEvent(registrationEvent);
    }
    @DeleteMapping("/deleteRegistrationEvent")
    public void  deleteRegistrationEvent(RegistrationEvent registrationEvent){
        iRegistrationEventService.deleteRegistrationEvent(registrationEvent);
    }

}
