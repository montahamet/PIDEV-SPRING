package com.coconsult.pidevspring.DAO.Repository.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.StatusTask;
import com.coconsult.pidevspring.DAO.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query("SELECT t FROM Task t WHERE t.projetT.projectId = :projectId")
    List<Task> findByProjetTProjectId(Long projectId);
    List<Task> findByTaskStatus(StatusTask status);

}
