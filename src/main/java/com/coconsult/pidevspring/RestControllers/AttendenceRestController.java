package com.coconsult.pidevspring.RestControllers;

import com.coconsult.pidevspring.DAO.Entities.Attendence;
import com.coconsult.pidevspring.Services.IAttendenceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/attendance") // Change endpoint to singular form
public class AttendenceRestController {
    private final IAttendenceService attendenceService;

    public AttendenceRestController(IAttendenceService attendenceService) {
        this.attendenceService = attendenceService;
    }

    @PostMapping("/start")
    public ResponseEntity<Long> startAttendance(@RequestParam Long userId) {
        Long attendanceId = attendenceService.startAttendance(userId);
        return ResponseEntity.ok(attendanceId);
    }

    @PostMapping("/end/{attendanceId}")
    public ResponseEntity<Void> endAttendance(@PathVariable Long attendanceId) {
        attendenceService.endAttendance(attendanceId);
        return ResponseEntity.ok().build(); // Indiquer le succès de la fin de l'assiduité
    }

    @GetMapping("/all") // Endpoint to retrieve all attendance records
    public List<Attendence> retrieveAllAttendence() {
        return attendenceService.retrieveAllAttendence();
    }

//        @PostMapping("/add") // Endpoint to add attendance
//        public Attendence addAttendence(@RequestBody Attendence attendance) {
//            attendance.setDate(LocalDateTime.now());
//            return attendenceService.addAttendence(attendance);
//        }
    //update pasing id user to add :
@PostMapping("/add/{userId}") // Endpoint to add attendance with user ID in the URL
public Attendence addAttendance(@PathVariable Long userId, @RequestBody Attendence attendance) {
<<<<<<< HEAD
    attendance.setDate(LocalDateTime.now());
    // Assuming attendanceService.addAttendance() handles the user ID internally
=======
    attendance.setStart(LocalDateTime.now());
    attendance.setEnd(LocalDateTime.now());
>>>>>>> 491a11e43d1e89df12b91b79fa5b0fa70aad9e0a
    return attendenceService.addAttendence(userId, attendance);
}


    @PutMapping("/update/{id}") // Endpoint to update attendance (if needed)
    public void updateAttendence(@PathVariable("id") Long id, @RequestBody Attendence attendance) {
        // You can implement this method if you need to update attendance
        // Note: You may need to handle security and validation checks here
         attendenceService.endAttendance(id);
    }

    @GetMapping("/get/{id}") // Endpoint to retrieve a specific attendance record by ID (if needed)
    public Attendence retrieveAttendence(@PathVariable("id") Long id) {
        return attendenceService.retrieveAttendence(id);
    }

    @DeleteMapping("/remove/{id}") // Endpoint to remove attendance by ID (if needed)
    public void removeAttendence(@PathVariable("id") Long id) {
        attendenceService.removeAttendence(id);
    }
}
