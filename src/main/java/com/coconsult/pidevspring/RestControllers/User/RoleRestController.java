package com.coconsult.pidevspring.RestControllers.User;

import com.coconsult.pidevspring.DAO.Entities.Role;
import com.coconsult.pidevspring.Services.User.IRoleService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/role")
public class RoleRestController {
    @Autowired

    IRoleService iRoleService;
    @GetMapping("/retrieveAllRoles")
   // @PreAuthorize("hasRole('ADMIN')")
    public List<Role> retrieveAllRoles() {
        List<Role> roles= iRoleService.retrieveAllRoles();
        return roles;
    }

    @PostMapping("/addRole")
    public Role addRole(@RequestBody Role r) {
        return iRoleService.addRole(r);
    }

    @PutMapping("/updateRole")
    public Role updateRole(@RequestBody Role r) {
        return iRoleService.updateRole(r);
    }

    @GetMapping("/retrieveOneRole/{roleId}")
    public Role retrieveOneUser(@PathVariable Long roleId) {
        return iRoleService.retrieveOneRole(roleId);
    }

    @DeleteMapping("/removeRole/{roleId}")
    public void removeUser(@PathVariable Long roleId) {
        iRoleService.removeRole(roleId);

    }
}
