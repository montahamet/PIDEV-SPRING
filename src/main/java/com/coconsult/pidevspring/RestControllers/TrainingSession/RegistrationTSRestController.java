package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.RegistrationEvent;
import com.coconsult.pidevspring.DAO.Entities.RegistrationTS;
import com.coconsult.pidevspring.Services.TrainingSession.IRegistrationTSService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/RegistrationTS-TrainingSession")

public class RegistrationTSRestController {
    IRegistrationTSService iRegistrationTSService;


        @PostMapping("/addRegistrationTS/{tsId}/{userId}")
    public ResponseEntity<?> addRegistrationTS(@PathVariable Long tsId, @PathVariable Long userId) {
        try {
            RegistrationTS registration = iRegistrationTSService.addRegistrationTS(tsId, userId);
            return ResponseEntity.ok(registration); // Retourne 200 OK avec le corps contenant l'objet registration
        } catch (OpenApiResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
    @GetMapping("/findOneRegistrationTS")
    public RegistrationTS findOneRegistrationTS(@PathParam("registrationTS_id") Long registrationTS_id){
        return iRegistrationTSService.findOneRegistrationTS(registrationTS_id);
    }
    @GetMapping("/findAllRegistrationTS")
    public List<RegistrationTS> findAllActivities() {
        return  iRegistrationTSService.findAllRegistrationTS();
    }
//    @PostMapping("/addRegistrationTS")
//    public  RegistrationTS addRegistrationTS(@RequestBody RegistrationTS registrationTS) {
//        return iRegistrationTSService.addRegistrationTS(registrationTS);
//    }
    @PutMapping("/UpdateRegistrationTS")
    public  RegistrationTS UpdateRegistrationTS(@RequestBody RegistrationTS registrationTS) {
        return iRegistrationTSService.UpdateRegistrationTS(registrationTS);
    }

    @DeleteMapping("/deleteRegistrationTS")
    public void  deleteRegistrationTS(Long registrationTS_id){
        iRegistrationTSService.deleteRegistrationTSById(registrationTS_id);
    }
    @GetMapping("/{sessionId}/is-registered/{userId}")
    public ResponseEntity<Boolean> isUserRegistered(@PathVariable Long sessionId, @PathVariable Long userId) {
        boolean isRegistered = iRegistrationTSService.isUserRegistered(sessionId, userId);
        return ResponseEntity.ok(isRegistered);
    }

    @PostMapping("/unregister")
    public ResponseEntity<String> unregisterFromTraining(@RequestParam Long userId, @RequestParam Long sessionId) {
        iRegistrationTSService.unregisterFromTraining(userId, sessionId);
        return ResponseEntity.ok("Unregistered successfully");
    }
}
