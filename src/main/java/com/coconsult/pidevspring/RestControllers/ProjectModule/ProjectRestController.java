package com.coconsult.pidevspring.RestControllers.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Project;
import com.coconsult.pidevspring.Services.ProjectModule.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

@RequestMapping("/Project")
public class ProjectRestController {
    IProjectService iProjectService;
    @GetMapping("/GetAllProjects")
    public List<Project> getAllProjects(){
       return iProjectService.getAllProjects();
    }
    @GetMapping("/GetProjectbyid")
    public Project findProjectById(@RequestParam long id){
        return iProjectService.getOneProject(id);
    }
    @PostMapping("/AddProject/{id}")
    public Project addProject(@RequestBody Project project,@PathVariable Long id){
        return iProjectService.addProject(project,id);
    }
    @PutMapping("/UpdateProject")
    public Project updateProject(@RequestBody Project project){
        return iProjectService.updateProject(project);
    }
    @DeleteMapping("/DeleteProjectbyid")
    public void removeProjectbyid(@RequestParam long projectid){
        iProjectService.removeProjectbyid(projectid);

    }
    @DeleteMapping("/DeleteProject")
    public void deleteProject(@RequestBody Project project){
        iProjectService.deleteProject(project);

    }
    @GetMapping("/searchProject")
    public List<Project> searchProjects(String keyword) {
        return  iProjectService.searchProjects(keyword);
    }

    }
