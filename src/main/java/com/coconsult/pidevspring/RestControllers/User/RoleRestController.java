package com.coconsult.pidevspring.RestControllers.User;

import com.coconsult.pidevspring.DAO.Entities.Role;
import com.coconsult.pidevspring.Services.User.IRoleService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("/role")
public class RoleRestController {

    IRoleService iRoleService;
    @GetMapping("/retrieveAllRoles")
    public List<Role> retrieveAllRoles() {
        List<Role> roles= iRoleService.retrieveAllRoles();
        return roles;
    }

    @PostMapping("/addRole")
    public Role addRole(Role r) {
        return iRoleService.addRole(r);
    }

    @PutMapping("/updateRole")
    public Role updateRole(@RequestBody Role r) {
        return iRoleService.updateRole(r);
    }

    @GetMapping("/retrieveOneRole")
    public Role retrieveOneUser(@PathParam("roleId") Long roleId) {
        return iRoleService.retrieveOneRole(roleId);
    }

    @DeleteMapping("/removeRole")
    public void removeUser(@PathParam("roleId") Long roleId) {
        iRoleService.removeRole(roleId);

    }
}
