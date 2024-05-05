package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.Attendence;

import java.util.List;

public interface IAttendenceService {

    public List<Attendence> retrieveAllAttendence();
    public Attendence retrieveAttendence(Long attendenceId);
//    public Attendence addAttendence(Attendence a);
    //update add with user id
public Attendence addAttendence(Long userId,Attendence a);

<<<<<<< HEAD
=======
    Long startAttendance(Long userId);

>>>>>>> 491a11e43d1e89df12b91b79fa5b0fa70aad9e0a
    public void removeAttendence(Long attendenceId);
    public Attendence modifyAttendence(Attendence attendence);

    void endAttendance(Long attendenceId);
}
