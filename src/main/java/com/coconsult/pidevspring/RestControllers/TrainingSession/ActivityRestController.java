package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Activity;
import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.Services.TrainingSession.IActivityService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/Activity-TrainingSession")

public class ActivityRestController {
    IActivityService iActivityService;


    @GetMapping("/findOneActivity")
    public Activity findOneActivity(@PathParam("Activity_id") Long Activity_id){
        return iActivityService.findOneActivity(Activity_id);
    }
    @GetMapping("/findAllActivities")
    public List<Activity> findAllActivities() {
        return  iActivityService.findAllActivities();
    }
    @PostMapping("/addActivity")
    public  Activity addActivity(@RequestBody Activity activity) {
        return iActivityService.addActivity(activity);
    }
    @PutMapping("/UpdateActivity")
    public  Activity UpdateActivity(@RequestBody Activity activity) {
        return iActivityService.UpdateActivity(activity);
    }
    @DeleteMapping("/deleteActivityById")
    public void deleteActivityById(@RequestParam("activity_id") Long activity_id) {
        iActivityService.deleteActivityById(activity_id);
    }
    @GetMapping("/getAllEventsWithName")
    public List<Event> getAllEventsWithName() {
        return iActivityService.getAllEventsWithName();
    }

}
