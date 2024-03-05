package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.TrainingSession;
import com.coconsult.pidevspring.Services.TrainingSession.ITrainingSessionService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/TrainingSession-TrainingSession")

public class TrainingSessionRestController {
    ITrainingSessionService iTrainingSessionService;

    @GetMapping("/findOneTrainingSession")
    public TrainingSession findOneTrainingSession(@PathParam("TS_id") Long TS_id){
        return iTrainingSessionService.findOneTrainingSession(TS_id);
    }
    @GetMapping("/findAllTrainingSession")
    public List<TrainingSession> findAllActivities() {
        return  iTrainingSessionService.findAllTrainingSession();
    }
    @PostMapping("/addTrainingSession")
    public  TrainingSession addTrainingSession(@RequestBody TrainingSession trainingSession) {
        return iTrainingSessionService.addTrainingSession(trainingSession);
    }
    @PutMapping("/UpdateTrainingSession")
    public  TrainingSession UpdateTrainingSession(@RequestBody TrainingSession trainingSession) {
        return iTrainingSessionService.UpdateTrainingSession(trainingSession);
    }

    @DeleteMapping("/deleteTrainingSessionById")
    public void  deleteTrainingSessionById(@PathParam("TS_id") Long TS_id){
        iTrainingSessionService.deleteTrainingSessionById(TS_id);
    }
}
