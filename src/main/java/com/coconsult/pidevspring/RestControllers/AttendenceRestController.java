package com.coconsult.pidevspring.RestControllers;

import com.coconsult.pidevspring.DAO.Entities.Attendence;
import com.coconsult.pidevspring.DAO.Entities.ProjectOffer;
import com.coconsult.pidevspring.Services.IAttendenceService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/attendence")
public class AttendenceRestController {
    IAttendenceService iAttendenceService;


    @GetMapping("/retrieve-attendences")
    public List<Attendence> retrieveAllAttendence() {
        List<Attendence> attendences= iAttendenceService.retrieveAllAttendence();
        return attendences;
    }

    @PostMapping("/add-attendence")
    public Attendence addAttendence(@RequestBody Attendence attendence) {
        Attendence attendence1 = iAttendenceService.addAttendence(attendence);

        return attendence1 ;
    }

    @PutMapping("/update-attendence")
    public Attendence updateAttendence(@RequestBody Attendence attendence) {

        return iAttendenceService.modifyAttendence(attendence);
    }

    @GetMapping("/retrieve-attendence")
    public Attendence retrieveProjectOffer(@PathParam("attendenceid") Long attendenceid) {
        Attendence attendence = iAttendenceService.retrieveAttendence(attendenceid);
        return attendence;
    }

    @DeleteMapping("/remove-attendence")
    public void removeAttendence(@PathParam("attendenceid") Long attendenceid) {
        iAttendenceService.removeAttendence(attendenceid);
    }


}
