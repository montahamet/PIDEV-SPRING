package com.coconsult.pidevspring.RestControllers.User;

import com.coconsult.pidevspring.DAO.Entities.Role;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.User.RoleRepository;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import com.coconsult.pidevspring.Security.payload.request.SignupRequest;
import com.coconsult.pidevspring.Security.payload.response.MessageResponse;
import com.coconsult.pidevspring.Services.User.IUserService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@AllArgsConstructor
@RequestMapping("/user")

public class UserRestController {
    @Autowired
    IUserService iUserService;
    @Autowired
    UserRepository userRepository  ;
    @Autowired
    RoleRepository roleRepository ;
    @Autowired
    PasswordEncoder encoder;
    @GetMapping("/retrieveAllUser")
    public List<User> retrieveAllUser() {
        List<User> users= iUserService.retrieveAllUser();
        return users;
    }

    @PostMapping("/addUser")
    public ResponseEntity<?>  addUser(@Valid @RequestBody SignupRequest signUpRequest){
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstname(signUpRequest.getFirstname());
        user.setLastname(signUpRequest.getLastname());
        user.setBirthdate(signUpRequest.getBirthdate());
        user.setGender(signUpRequest.getGender());
        user.setPhonenumber(signUpRequest.getPhonenumber());
        user.setAdresse(signUpRequest.getAdresse());
        user.setPassword(encoder.encode(signUpRequest.getPassword())    );
        user.setImage(signUpRequest.getImage());
        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByRoleName("admin")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByRoleName("admin")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "pm":
                        Role pmRole = roleRepository.findByRoleName("Project manager")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(pmRole);

                        break;
                    case "hr":
                        Role hrRole = roleRepository.findByRoleName("HR")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(hrRole);

                        break;
                    case "consultant":
                        Role cRole = roleRepository.findByRoleName("Consultant")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(cRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByRoleName("Employee")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        iUserService.addUser(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
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



    @PostMapping("/save")
    public ResponseEntity<String> saveUsers(@RequestBody List<User> users) {
        try {
            userRepository.saveAll(users);
            return ResponseEntity.ok("Users saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving users: " + e.getMessage());
        }
    }

    //malekkk
    @GetMapping("/projectmanagers")
    public List<User> getProjectManagers() {
        return iUserService.getProjectManagers();
    }
    // malekk
    @GetMapping("/employees")

    public List<User> getEmployeesForTASKS(){
        return iUserService.getEmployeesForTASKS();

    }
    //malekk
    @GetMapping("/competentUsers")
    public List<User> getCompetentUsers() {
        return iUserService.findCompetentUsersOrderByTasks();

    }
}
