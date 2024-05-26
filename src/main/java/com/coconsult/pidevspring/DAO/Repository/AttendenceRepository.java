package com.coconsult.pidevspring.DAO.Repository;

import com.coconsult.pidevspring.DAO.Entities.Attendence;
import com.coconsult.pidevspring.DAO.Entities.ProjectOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendenceRepository extends JpaRepository<Attendence,Long> {
    // Count attendances for the current month
    @Query("SELECT COUNT(a) FROM Attendence a WHERE a.employee.userId = :userId AND MONTH(a.start) = MONTH(CURRENT_DATE)  AND a.approved=false ")
    long countAttendancesForCurrentMonth(Long userId);

    // Count attendances for the current year
    @Query("SELECT COUNT(a) FROM Attendence a WHERE a.employee.userId = :userId AND YEAR(a.start) = YEAR(CURRENT_DATE) AND a.approved=false")
    long countAttendancesForCurrentYear(Long userId);

    List<Attendence> findByEmployeeUserId(Long userId);

}
