package com.coconsult.pidevspring.RestControllers.User;

import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.Services.User.IUserService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    IUserService iUserService;
    @GetMapping("/retrieveAllUser")
    public List<User> retrieveAllUser() {
        List<User> users= iUserService.retrieveAllUser();
        return users;
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User u) {
        return iUserService.addUser(u);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User u) {
        return iUserService.updateUser(u);
    }

    @GetMapping("/retrieveOneUser/{userId}")
    public User retrieveOneUser(@PathVariable Long userId) {
        return iUserService.retrieveOneUser(userId);
    }

    @DeleteMapping("/removeUser/{userId}")
    public void removeUser(@PathVariable Long userId) {
        iUserService.removeUser(userId);

    }
}
