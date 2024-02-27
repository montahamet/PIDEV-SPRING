package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Task;
import com.coconsult.pidevspring.DAO.Repository.ProjectModule.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService implements ITaskService {
    TaskRepository taskRepository;
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll() ;
    }




    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getOneTask(long Taskid) {
        return taskRepository.findById(Taskid).get();
    }

    @Override
    public void removeTaskbyid(long Taskid) {
        taskRepository.deleteById(Taskid);

    }

    @Override
    public void deleteTask(Task task) {
        taskRepository.delete(task);

    }
}
