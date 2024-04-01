package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Project;
import com.coconsult.pidevspring.DAO.Entities.StatusTask;
import com.coconsult.pidevspring.DAO.Entities.Task;
import com.coconsult.pidevspring.DAO.Entities.Task;

import java.util.List;

public interface ITaskService {
    List<Task> getAllTasks();
    List<Task> getPaginatedTasks(int page, int pageSize);


    Task addTask(Task task);

    Task updateTask(Task task);

    Task getOneTask(long Taskid);

    void removeTaskbyid(long Taskid);
    void deleteTask(Task task);
    List<Task> getTasksByProjectId(Long projectId);
    void affecterTaskAuProjet(Task task, String projectName);
    List<String> getAllProjectNames();
    List<Task> getTasksByStatus(StatusTask status);

}
