package com.coconsult.pidevspring.DAO.Repository.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
