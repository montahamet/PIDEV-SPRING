package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.Attendence;
import com.coconsult.pidevspring.DAO.Repository.AttendenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendenceService implements IAttendenceService {

    private final AttendenceRepository attendenceRepository;

    public AttendenceService(AttendenceRepository attendenceRepository) {
        this.attendenceRepository = attendenceRepository;
    }

    @Override
    public List<Attendence> retrieveAllAttendence() {
        return attendenceRepository.findAll();
    }

    @Override
    public Attendence retrieveAttendence(Long attendenceId) {
        return attendenceRepository.findById(attendenceId).orElse(null);
    }

    @Override
    public Attendence addAttendence(Attendence attendence) {
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
