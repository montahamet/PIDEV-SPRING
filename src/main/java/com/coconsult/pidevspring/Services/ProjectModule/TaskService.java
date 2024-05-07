package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Project;
import com.coconsult.pidevspring.DAO.Entities.StatusTask;
import com.coconsult.pidevspring.DAO.Entities.Task;
import com.coconsult.pidevspring.DAO.Repository.ProjectModule.ProjectRepository;
import com.coconsult.pidevspring.DAO.Repository.ProjectModule.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService implements ITaskService {
    @Autowired

    TaskRepository taskRepository;
    private ProjectService projectService;
    ProjectRepository projectRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getPaginatedTasks(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return taskRepository.findAll(pageable).getContent();
    }


    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
        // Find the existing job offer by ID
        Optional<Task> existingJobOffer = taskRepository.findById(taskId);

        // Check if the job offer exists
        if (existingJobOffer.isPresent()) {
            // Update the fields of the existing job offer with the details provided
            Task existingJobOfferEntity = existingJobOffer.get();
            existingJobOfferEntity.setTaskname(task.getTaskname());
            existingJobOfferEntity.setStartDateTask(task.getStartDateTask());
            existingJobOfferEntity.setProgress(task.getProgress());
            existingJobOfferEntity.setDuration(task.getDuration());
            existingJobOfferEntity.setParent(task.getParent());
            existingJobOfferEntity.setDueDateTask(task.getDueDateTask());
            existingJobOfferEntity.setTaskDescription(task.getTaskDescription());
            existingJobOfferEntity.setPriority(task.getPriority());
            existingJobOfferEntity.setTaskStatus(task.getTaskStatus());
            existingJobOfferEntity.setProjetT(task.getProjetT());
            existingJobOfferEntity.setEmployeeTask(task.getEmployeeTask());

            // Save the updated job offer back to the database
            return taskRepository.save(existingJobOfferEntity);
        } else {
            // Handle the case where the job offer does not exist
            // This could be throwing an exception, logging a message, etc.
            throw new RuntimeException("task not found with id " + taskId);
        }
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

    @Override
    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.findByProjetTProjectId(projectId);
    }

    @Override
    public void affecterTaskAuProjet(Task task, String projectName) {
        Project project = projectRepository.findByProjectName(projectName);

        if (project != null) {
            task.setProjetT(project);
            taskRepository.save(task);
        } else System.out.println("Le projet avec le nom n'existe pas.");
    }

    @Override

    public List<String> getAllProjectNames() {
        return projectService.getAllProjectNames();
    }


    @Override
    public List<Task> getTasksByStatus(StatusTask status) {
        return taskRepository.findByTaskStatus(status);
    }

    @Override
    public long countByEmployeeTaskUserId(long userId) {
        return taskRepository.countByEmployeeTaskUserId(userId);
    }

    @Override
    public boolean existsByEmployeeTaskUserIdAndDueDateTaskAfter(long userId) {
        return taskRepository.existsByEmployeeTaskUserIdAndDueDateTaskAfter(userId);
    }

    @Override
    public List<Task> searchTasks(String keyword) {
        LocalDate startDate = null;
        try {
            startDate = LocalDate.parse(keyword);
        } catch (DateTimeParseException e) {
        }

        if (startDate != null) {
            return taskRepository.findByTaskDescriptionContainingIgnoreCaseOrStartDateTask(keyword,startDate);
        } else {
            return taskRepository.findByTaskDescriptionContainingIgnoreCaseOrStartDateTask(keyword,startDate);
        }
    }
    }



