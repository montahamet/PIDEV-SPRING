package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Activity;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.Services.TrainingSession.IActivityService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
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
    public  Activity addActivity(Activity activity) {
        return iActivityService.addActivity(activity);
    }
    @PostMapping("/updateActivity")
    public Activity updateActivity(@RequestBody Activity activity){
        return iActivityService.updateActivity(activity);
    }
    @DeleteMapping("/deleteActivity")
    public void  deleteActivity(Activity activity){
        iActivityService.deleteActivity(activity);
    }

}
