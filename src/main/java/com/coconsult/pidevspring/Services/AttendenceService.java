package com.coconsult.pidevspring.Services;


import com.coconsult.pidevspring.DAO.Entities.Attendence;
import com.coconsult.pidevspring.DAO.Repository.AttendenceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AttendenceService implements IAttendenceService {

    AttendenceRepository attendenceRepository;
    @Override
    public List<Attendence> retrieveAllAttendence() {
        return attendenceRepository.findAll();
    }

    @Override
    public Attendence retrieveAttendence(Long attendenceId) {
        return attendenceRepository.findById(attendenceId).get();
    }

    @Override
    public Attendence addAttendence(Attendence a) {
        return attendenceRepository.save(a);
    }

    @Override
    public void removeAttendence(Long attendenceId) {
        attendenceRepository.deleteById(attendenceId);
    }

    @Override
    public Attendence modifyAttendence(Attendence attendence) {
        Attendence attendence1=attendenceRepository.save(attendence);
        return attendence1;
    }
}
