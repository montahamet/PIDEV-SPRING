package com.coconsult.pidevspring.Services;


import com.coconsult.pidevspring.DAO.Entities.Leaves;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.LeaveRepository;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LeaveService implements ILeaveService {

    LeaveRepository attendenceRepository;
    UserRepository userRepository ;
    @Override
    public List<Leaves> retrieveAllLeave() {
        return attendenceRepository.findLeavesByApproved(false);
    }

    @Override
    public Leaves retrieveLeave(Long attendenceId) {
        return attendenceRepository.findById(attendenceId).get();
    }

    @Override
    public Leaves addLeave(Leaves a, Long id) {
        User user = userRepository.findById(id).get();
        a.setEmployee(user);
        a.setApproved(false);


        return attendenceRepository.save(a);
    }

    @Override
    public void removeLeave(Long attendenceId) {
        attendenceRepository.deleteById(attendenceId);
    }

    @Override
    public Leaves modifyLeave(Leaves attendence) {
        Leaves attendence1=attendenceRepository.save(attendence);
        return attendence1;
    }

    @Override
    public Leaves aprouveLeave(Leaves attendence) {

        attendence.setApproved(true);
        return  attendenceRepository.save(attendence);

    }
}
