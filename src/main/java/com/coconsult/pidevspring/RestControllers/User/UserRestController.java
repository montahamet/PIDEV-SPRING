package com.coconsult.pidevspring.RestControllers.User;

import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.Services.User.IUserService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("/user")
public class UserRestController {

    IUserService iUserService;
    @GetMapping("/retrieveAllUser")
    public List<User> retrieveAllUser() {
        List<User> users= iUserService.retrieveAllUser();
        return users;
    }

    @PostMapping("/addUser")
    public User addUser( User u) {
        return iUserService.addUser(u);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User u) {
        return iUserService.updateUser(u);
    }

    @GetMapping("/retrieveOneUser")
    public User retrieveOneUser(@PathParam("userId") Long userId) {
        return iUserService.retrieveOneUser(userId);
    }

    @DeleteMapping("/removeUser")
    public void removeUser(@PathParam("userId") Long userId) {
        iUserService.removeUser(userId);

    }
}
