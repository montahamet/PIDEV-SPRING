package com.coconsult.pidevspring.RestControllers.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Task;
import com.coconsult.pidevspring.Services.ProjectModule.ITaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("/Task")
public class TaskRestController {
    ITaskService iTaskService;
    @GetMapping("/GetAllTasks")
    public List<Task> getAllTasks(){
        return iTaskService.getAllTasks();
    }
    @GetMapping("/GetTaskbyid")
    public Task getOneTask(@RequestParam long Taskid){
        return iTaskService.getOneTask(Taskid);
    }
    @PostMapping("/AddTask")
    public Task addTask(@RequestBody Task task){
        return iTaskService.addTask(task);
    }
    @PutMapping("/UpdateTask")
    public Task updateTask(@RequestBody Task task){
        return iTaskService.updateTask(task);
    }
    @DeleteMapping("/DeleteTaskbyid")
    public void removeTaskbyid(@RequestParam long Taskid){
        iTaskService.removeTaskbyid(Taskid);
    }
    @DeleteMapping("/DeleteTask")
    public void deleteTask(@RequestBody Task task){
        iTaskService.deleteTask(task);
    }
}
