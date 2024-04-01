package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Project;
import com.coconsult.pidevspring.DAO.Repository.ProjectModule.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService implements IProjectService {
    ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }




    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getOneProject(long projectid) {
        return projectRepository.findById(projectid).get();
    }

    @Override
    public void removeProjectbyid(long projectid) {
        projectRepository.deleteById(projectid);

    }

    @Override
    public void deleteProject(Project project) {
        projectRepository.delete(project);

    }

    @Override
    public List<String> getAllProjectNames() {
        return projectRepository.getAllProjectNames();
    }

    @Override
    public Project findByProjectName(String projectName) {
        return projectRepository.findByProjectName(projectName);
    }

    @Override
    public List<Project> searchProjects(String keyword) {
        LocalDate startDate = null;
        try {
            startDate = LocalDate.parse(keyword);
        } catch (DateTimeParseException e) {
        }

        if (startDate != null) {
            return projectRepository.findByProjectNameContainingIgnoreCaseOrStartdateProject(keyword, startDate);
        } else {
            return projectRepository.findByProjectNameContainingIgnoreCaseOrStartdateProject(keyword,startDate);
        }
    }

    }


