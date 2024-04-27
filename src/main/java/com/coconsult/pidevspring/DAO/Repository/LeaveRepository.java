package com.coconsult.pidevspring.DAO.Repository;

import com.coconsult.pidevspring.DAO.Entities.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leaves,Long> {
    List<Leaves> findLeavesByApproved(boolean a);

}
