package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.Role;
import com.coconsult.pidevspring.DAO.Entities.Role;
import com.coconsult.pidevspring.DAO.Repositories.RoleRepository;
import com.coconsult.pidevspring.DAO.Repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService implements IRoleService {

    RoleRepository roleRepository ;
    @Override
    public List<Role> retrieveAllRole() {
        return roleRepository.findAll();
    }
    @Override
    public Role addRole(Role u) {
        return roleRepository.save(u);
    }

    @Override
    public Role updateRole(Role u) {
        return roleRepository.save(u);
    }

    @Override
    public Role retrieveOneRole(Long RoleId) {
        return roleRepository.findById(RoleId).get();
    }

    @Override
    public void removeRole(Long RoleID) {
        roleRepository.deleteById(RoleID);

    }
}
