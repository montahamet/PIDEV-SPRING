package com.coconsult.pidevspring.DAO.Repositories.User;

import com.coconsult.pidevspring.DAO.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
