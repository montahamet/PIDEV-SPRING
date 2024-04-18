package com.coconsult.pidevspring.DAO.Repository.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    @Query("SELECT p.projectName FROM Project p")
    List<String> getAllProjectNames();
    Project findByProjectName(String projectName);

}
