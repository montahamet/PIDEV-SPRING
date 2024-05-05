package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.Attendence;

import java.util.List;

public interface IAttendenceService {

    public List<Attendence> retrieveAllAttendence();
    public Attendence retrieveAttendence(Long attendenceId);
//    public Attendence addAttendence(Attendence a);
    //update add with user id
public Attendence addAttendence(Long userId,Attendence a);

    public void removeAttendence(Long attendenceId);
    public Attendence modifyAttendence(Attendence attendence);
}
