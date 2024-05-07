package com.coconsult.pidevspring.RestControllers.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Project;
import com.coconsult.pidevspring.DAO.Entities.StatusTask;
import com.coconsult.pidevspring.DAO.Entities.Task;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.ProjectModule.ProjectRepository;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import com.coconsult.pidevspring.Services.ProjectModule.ITaskService;
import com.coconsult.pidevspring.Services.User.IUserService;
import com.coconsult.pidevspring.Services.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

@RequestMapping("/Task")
public class TaskRestController {
    ITaskService iTaskService;
UserRepository userRepository;
ProjectRepository projectRepository;
UserService userService;
    @GetMapping("/GetAllTasks")
    public List<Task> getAllTasks() {
        return iTaskService.getAllTasks();
    }

    @GetMapping("/GetTaskbyid")
    public Task getOneTask(@RequestParam long Taskid) {
        return iTaskService.getOneTask(Taskid);
    }

//    @PostMapping("/AddTask/{projectId}/{userId}")
//    public Task addTask(@RequestBody Task task, @PathVariable("projectId") long projectId, @PathVariable("userId") long userId) {
//        // Vous devez récupérer le projet et l'utilisateur correspondants à partir de leur ID
//        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + projectId));
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID : " + userId));
//
//        // Assurez-vous que la tâche est liée au projet et à l'utilisateur appropriés
//        task.setProjetT(project);
//        task.setEmployeeTask(user);
//
//        // Ensuite, vous pouvez ajouter la tâche via le service
//        return iTaskService.addTask(task);
//    }
//@PostMapping("/AddTask/{projectId}")
//public Task addTask(@RequestBody Task task, @PathVariable("projectId") long projectId) {
//    // Récupérer le projet correspondant à partir de son ID
//    Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + projectId));
//
//    // Récupérer la liste des employés disponibles
//    List<User> employees = userService.getEmployeesForTASKS();
//
//    // Si aucun employé n'est disponible, vous pouvez gérer cela ici selon vos besoins
//
//    // Assurez-vous qu'il y a au moins un employé disponible avant d'attribuer la tâche
//    if (employees.isEmpty()) {
//        throw new RuntimeException("Aucun employé disponible pour attribuer la tâche.");
//    }
//
//    // Vous pouvez attribuer la tâche à un employé, par exemple, au premier de la liste
//    User user = employees.get(0);
//
//    // Assurez-vous que la tâche est liée au projet et à l'utilisateur appropriés
//    task.setProjetT(project);
//    task.setEmployeeTask(user);
//
//    // Ensuite, vous pouvez ajouter la tâche via le service
//    return iTaskService.addTask(task);
//}
@PostMapping("/AddTask/{projectId}/{userId}")
    public Task addTask(@RequestBody Task task,
                    @PathVariable("projectId") long projectId,
                    @PathVariable("userId") long userId) {
    // Récupérer le projet correspondant à partir de son ID
    Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + projectId));
    System.out.println("****************"+project);

    // Récupérer la liste des employés ayant le rôle "EMPLOYEE"
    List<User> employees = userService.getEmployeesForTASKS();

    // Vérifier si aucun employé n'est disponible
    if (employees.isEmpty()) {
        throw new RuntimeException("Aucun employé disponible pour attribuer la tâche.");
    }

    // Récupérer l'utilisateur correspondant à partir de son ID
    User user = employees.stream()
            .filter(emp -> emp.getUserId() == userId)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID : " + userId));

    // Assurez-vous que la tâche est liée au projet et à l'utilisateur appropriés
    task.setProjetT(project);
    task.setEmployeeTask(user);

    // Ensuite, vous pouvez ajouter la tâche via le service
    return iTaskService.addTask(task);
}


    @PutMapping("/UpdateTask/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        try {
            Task updatedtask = iTaskService.updateTask(taskId,task);
            return ResponseEntity.ok(updatedtask);
        } catch (RuntimeException e) {
            // Handle the case where the job offer does not exist
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/DeleteTaskbyid")
    public void removeTaskbyid(@RequestParam long Taskid) {
        iTaskService.removeTaskbyid(Taskid);
    }

    @DeleteMapping("/DeleteTask")
    public void deleteTask(@RequestBody Task task) {
        iTaskService.deleteTask(task);
    }

    @GetMapping("/tasksbythree")

    public List<Task> getPaginatedTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return iTaskService.getPaginatedTasks(page, pageSize);
    }

    @GetMapping("/getTasksbyproject")
    public List<Task> getTasksByProjectId(Long projectId) {
        return iTaskService.getTasksByProjectId(projectId);
    }

    @PutMapping("/affecterTaskaunprojet")
    public void addTaskWithProject(@RequestBody Task task, @RequestParam String projectName) {
        iTaskService.affecterTaskAuProjet(task, projectName);
    }

    @GetMapping("/getprojectNames")
    public List<String> getAllProjectNames() {
        return iTaskService.getAllProjectNames();

    }


    @GetMapping("/tasks")
    public List<Task> getTasksByStatus(@RequestParam String status) {
        return iTaskService.getTasksByStatus(StatusTask.valueOf(status.toUpperCase()));
    }

    @GetMapping("/countByEmployeeTask")
    public long countByEmployeeTaskUserId(long userId) {
        return iTaskService.countByEmployeeTaskUserId(userId);

    }
    @GetMapping("/available")
    public boolean existsByEmployeeTaskUserIdAndDueDateTaskAfter(long userId){
        return iTaskService.existsByEmployeeTaskUserIdAndDueDateTaskAfter(userId);
    }
    @GetMapping("/searchTasks")
    public List<Task> searchTasks(String keyword) {
        return iTaskService.searchTasks(keyword);
    }
}