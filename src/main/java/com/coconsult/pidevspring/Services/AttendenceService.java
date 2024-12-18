package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.Attendence;

import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.AttendenceRepository;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendenceService implements IAttendenceService {

    private final AttendenceRepository attendenceRepository;
    private final UserRepository userRepository;

    public AttendenceService(AttendenceRepository attendenceRepository,UserRepository userRepository) {
        this.attendenceRepository = attendenceRepository;
        this.userRepository=userRepository;
    }

    @Override
    public List<Attendence> retrieveAllAttendence() {
        return attendenceRepository.findAll();
    }

    @Override
    public Attendence retrieveAttendence(Long attendenceId) {
        return attendenceRepository.findById(attendenceId).orElse(null);
    }

    //    @Override
//    public Attendence addAttendence(Attendence attendence) {
//        // Optionally, you can add validation logic here before saving the attendance record
//        return attendenceRepository.save(attendence);
//    }
    //update add user id to patth add
    @Override
    public Attendence addAttendence(Long userId,Attendence attendence) {
        User user = new User();
        user= userRepository.findById(userId).get();
        attendence.setEmployee(user);
        // Optionally, you can add validation logic here before saving the attendance record
        return attendenceRepository.save(attendence);
    }
    //find attendance with employee id
    @Override
    public List<Attendence> findByEmployeeId(Long UserId){
        List<Attendence> attendences = attendenceRepository.findByEmployeeUserId(UserId);
        return attendences;

    }


    @Override
    public void approved(Long UserId){
        List<Attendence> attendences = attendenceRepository.findByEmployeeUserId(UserId);
        List<Attendence> currentattences = new ArrayList<>();
        for (Attendence attendence : attendences) {
            // Assuming you have a method to construct the URL for the images
            if (attendence.getStart().getDayOfMonth() == LocalDateTime.now().getDayOfMonth() &&
                    attendence.getStart().getMonth() == LocalDateTime.now().getMonth() &&
                    attendence.getStart().getYear() == LocalDateTime.now().getYear()) {
                currentattences.add(attendence);
            }

        }
        for (Attendence attendence : currentattences) {
            attendence.setApproved(true);
            attendenceRepository.save(attendence);
        }
    }




    @Override
    public void removeAttendence(Long attendenceId) {
        attendenceRepository.deleteById(attendenceId);
    }

    @Override
    public Attendence modifyAttendence(Attendence attendence) {
        return null;
    }
    @Override
    public Long startAttendance(Long userId) {
        Attendence attendence = new Attendence();
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            attendence.setEmployee(user);
            attendence.setPresence(true);
            attendence.setStart(LocalDateTime.now());
            attendence.setEnd(null);
            attendence.setWorkedHours(0.0); // Utiliser 0.0 pour un double
            attendence = attendenceRepository.save(attendence);
            return attendence.getAttendenceId();
        } else {
            throw new RuntimeException("Utilisateur avec l'ID " + userId + " non trouvé");
        }
    }
    @Override
    public void endAttendance(Long attendenceId) {
        Optional<Attendence> attendanceOptional = attendenceRepository.findById(attendenceId);
        if (attendanceOptional.isPresent()) {
            Attendence attendance = attendanceOptional.get();
            attendance.setEnd(LocalDateTime.now());
            Duration duration = Duration.between(attendance.getStart(), attendance.getEnd());
            double workedHours = duration.toMinutes();
            attendance.setWorkedHours(workedHours);
            attendenceRepository.save(attendance);
        } else {
            throw new RuntimeException("Entrée d'assistance avec l'ID " + attendenceId + " non trouvée");
        }
    }
}