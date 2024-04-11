package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Activity;
import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.Services.TrainingSession.IActivityService;
import com.coconsult.pidevspring.Services.TrainingSession.IEventService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@AllArgsConstructor
@RequestMapping("/Activity-TrainingSession")

public class ActivityRestController {
    @Autowired
    IActivityService iActivityService;
    IEventService iEventService;


    @GetMapping("/findOneActivity/{activity_id}")
    public Activity findOneActivity(@PathVariable("activity_id") Long activity_id) {
        return iActivityService.findOneActivity(activity_id);
    }

    @GetMapping("/findAllActivities")
    public Page<Activity> findAllActivities(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return iActivityService.findAllActivities(pageable);
    }
    @PostMapping("/addActivity/{event_id}")
    public  Activity addActivity(@RequestBody Activity activity, @PathVariable("event_id") long event_id) {
        Event event = iEventService.findOneEvent(event_id);
        activity.setEvent(event);
        return iActivityService.addActivity(activity);
    }
    @PutMapping("/updateActivity/{activity_id}/{event_id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long activity_id, @PathVariable Long event_id, @RequestBody Activity activityDetails) {
        Activity updatedActivity = iActivityService.updateActivity(activity_id, activityDetails, event_id);
        return ResponseEntity.ok(updatedActivity);
    }




    @DeleteMapping("/deleteActivityById")
    public void deleteActivityById(@RequestParam("activity_id") Long activity_id) {
        iActivityService.deleteActivityById(activity_id);
    }
    @GetMapping("/getAllEventsWithName")
    public List<Event> getAllEventsWithName() {
        return iActivityService.getAllEventsWithName();
    }

//    @PutMapping("/activities/{id}")
//    public ResponseEntity<Activity> updateActivity(@PathVariable Long id, @RequestBody Activity updatedActivity) {
//        Activity existingActivity = iActivityService.findOneActivity(id);
//        if (existingActivity == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Mettre à jour les champs de l'activité existante avec les valeurs de l'activité mise à jour
//        existingActivity.setActivity_name(updatedActivity.getActivity_name());
//        existingActivity.setDescription(updatedActivity.getDescription());
//        existingActivity.setStartTime(updatedActivity.getStartTime());
//        existingActivity.setFinishTime(updatedActivity.getFinishTime());
//        existingActivity.setEvent(updatedActivity.getEvent()); // Assurez-vous que la relation est correctement gérée ici
//
//        Activity savedActivity = iActivityService.UpdateActivity(existingActivity);
//        return ResponseEntity.ok(savedActivity);
//    }



}
