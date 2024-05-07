package com.coconsult.pidevspring.DAO.Repository.User;

import com.coconsult.pidevspring.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findUserByEmail(String email) ;
    Boolean existsByEmail(String email);
    User findUserByResetPasswordToken(String token);
    ///malekkk
    List<User> findByRolesRoleName(String roleName);

    ///malekkk
   @Query(  "SELECT u FROM User u " +
            "JOIN FETCH u.employeeTasks t " +
            "WHERE SIZE(u.employeeTasks) > 0 " +
            "AND EXISTS (SELECT t2 FROM Task t2 " +
            "            WHERE t2.employeeTask = u " +
            "            AND (t2.dueDateTask - t2.startDateTask) = " +
            "                (SELECT MIN(t3.dueDateTask - t3.startDateTask) FROM Task t3 " +
            "                 WHERE t3.employeeTask = u)) " +
            "ORDER BY SIZE(u.employeeTasks) DESC")
    List<User> findCompetentUsersOrderByTasks();


    // New method to fetch only the email by user ID
    @Query("SELECT u.email FROM User u WHERE u.userId = :userId")
    Optional<Long> findEmailById(Long userId);
}
