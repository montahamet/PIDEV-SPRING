package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.Attendence;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.AttendenceRepository;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void removeAttendence(Long attendenceId) {
        attendenceRepository.deleteById(attendenceId);
    }

    @Override
    public Attendence modifyAttendence(Attendence attendence) {
        // Optionally, you can add validation logic here before modifying the attendance record
        return attendenceRepository.save(attendence);
    }
}
