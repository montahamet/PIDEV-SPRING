package com.coconsult.pidevspring.DAO.Repository.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.StatusTask;
import com.coconsult.pidevspring.DAO.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query("SELECT t FROM Task t WHERE t.projetT.projectId = :projectId")
    List<Task> findByProjetTProjectId(Long projectId);
    List<Task> findByTaskStatus(StatusTask status);
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Task t WHERE t.employeeTask.userId = :userId AND t.dueDateTask > CURRENT_DATE")
    boolean existsByEmployeeTaskUserIdAndDueDateTaskAfter(@Param("userId") long userId);
    long countByEmployeeTaskUserId(long userId);

}
