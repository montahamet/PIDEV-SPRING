package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Project;
import com.coconsult.pidevspring.DAO.Entities.User;

import java.util.List;

public interface IProjectService {
    List<Project> getAllProjects();

    Project addProject(Project project);

    Project updateProject(Project project);

    Project getOneProject(long projectid);

    void removeProjectbyid(long projectid);
    void deleteProject(Project project);
    List<String> getAllProjectNames();
    Project findByProjectName(String projectName);
    List<Project> searchProjects(String keyword);




}
