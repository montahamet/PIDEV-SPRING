package com.coconsult.pidevspring.RestControllers;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.Services.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    @PreAuthorize("hasRole('admin')")
    public String allAccess() {
        return "Public Content.";
    }
    @Autowired
    IUserService iUserService;
    @GetMapping("/retrieveAllUser")
    public List<User> retrieveAllUser() {
        List<User> users= iUserService.retrieveAllUser();
        return users;
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('user') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('admin')")
    public String adminAccess() {
        return "Admin Board.";
    }
}