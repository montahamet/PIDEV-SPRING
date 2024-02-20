package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Task;
import com.coconsult.pidevspring.DAO.Entities.Task;

import java.util.List;

public interface ITaskService {
    List<Task> getAllTasks();


    Task addTask(Task task);

    Task updateTask(Task task);

    Task getOneTask(long Taskid);

    void removeTaskbyid(long Taskid);
    void deleteTask(Task task);
}
