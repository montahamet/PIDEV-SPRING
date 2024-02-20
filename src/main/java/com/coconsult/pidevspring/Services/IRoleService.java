package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.Role;

import java.util.List;

public interface IRoleService {
    List<Role> retrieveAllRole();

    Role addRole(Role r);

    Role updateRole(Role r);

    Role retrieveOneRole(Long roleId);

    void removeRole(Long roleId);
}
