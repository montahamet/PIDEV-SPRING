package com.coconsult.pidevspring.DAO.Repositories;

import com.coconsult.pidevspring.DAO.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
