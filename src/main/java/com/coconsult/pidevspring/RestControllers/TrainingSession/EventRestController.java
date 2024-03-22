package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Activity;
import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.Services.TrainingSession.IEventService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/Event-TrainingSession")

public class EventRestController {
    IEventService iEventService;

    @GetMapping("/findOneEvent/{eventId}")
    public Event findOneEvent(@PathVariable("eventId") Long eventId){
        return iEventService.findOneEvent(eventId);
    }
    @GetMapping("/findAllEvents")
    public List<Event> findAllEvent() {
        return  iEventService.findAllEvent();
    }
    @PostMapping("/addEvent")
    public  Event addEvent(@RequestBody Event event) {
        return iEventService.addEvent(event);
    }
    @PutMapping("/UpdateEvent")
    public  Event UpdateEvent(@RequestBody Event event) {
        return iEventService.UpdateEvent(event);
    }
    @DeleteMapping("/deleteEvent/{eventId}")
    public void deleteEvent(@PathVariable("eventId") Long eventId) {
        iEventService.deleteEventById(eventId);
    }

}
