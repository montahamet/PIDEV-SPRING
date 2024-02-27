package com.coconsult.pidevspring.DAO.Repositories.User;

import com.coconsult.pidevspring.DAO.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RoleRepository extends JpaRepository<Role,Long> {
}
