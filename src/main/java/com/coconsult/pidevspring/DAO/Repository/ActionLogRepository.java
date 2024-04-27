package com.coconsult.pidevspring.DAO.Repository;

import com.coconsult.pidevspring.DAO.Entities.ActionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionLogRepository extends JpaRepository<ActionLog, Long> {

    @Query("SELECT a FROM ActionLog a WHERE a.timestamp = (SELECT MAX(al.timestamp) FROM ActionLog al WHERE al.entityId = a.entityId)  AND a.action <> 'DELETE'")
    List<ActionLog> findLastActionsOfEachEntity();
    List<ActionLog> findActionLogByEntityId(Long entityId);
}

