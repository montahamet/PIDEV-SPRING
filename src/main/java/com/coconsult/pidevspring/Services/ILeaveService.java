package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.Leaves;

import java.util.List;

public interface ILeaveService {

    public List<Leaves> retrieveAllLeave();
    public Leaves retrieveLeave(Long attendenceId);
    public Leaves addLeave(Leaves a ,Long id);
    public void removeLeave(Long attendenceId);
    public Leaves modifyLeave(Leaves attendence);
    public Leaves aprouveLeave(Leaves attendence);
}
