package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.RegistrationEvent;
import com.coconsult.pidevspring.DAO.Entities.RegistrationTS;
import com.coconsult.pidevspring.Services.TrainingSession.IRegistrationTSService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/RegistrationTS-TrainingSession")

public class RegistrationTSRestController {
    IRegistrationTSService iRegistrationTSService;

    @GetMapping("/findOneRegistrationTS")
    public RegistrationTS findOneRegistrationTS(@PathParam("registrationTS_id") Long registrationTS_id){
        return iRegistrationTSService.findOneRegistrationTS(registrationTS_id);
    }
    @GetMapping("/findAllRegistrationTS")
    public List<RegistrationTS> findAllActivities() {
        return  iRegistrationTSService.findAllRegistrationTS();
    }
    @PostMapping("/addRegistrationTS")
    public  RegistrationTS addRegistrationTS(@RequestBody RegistrationTS registrationTS) {
        return iRegistrationTSService.addRegistrationTS(registrationTS);
    }
    @PostMapping("/updateRegistrationTS")
    public RegistrationTS updateRegistrationTS(@RequestBody RegistrationTS registrationTS){
        return iRegistrationTSService.updateRegistrationTS(registrationTS);
    }
    @DeleteMapping("/deleteRegistrationTS")
    public void  deleteRegistrationTS(RegistrationTS registrationTS){
        iRegistrationTSService.deleteRegistrationTS(registrationTS);
    }
}
