package com.coconsult.pidevspring.RestControllers.User;

import com.coconsult.pidevspring.DAO.Entities.Role;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.User.RoleRepository;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import com.coconsult.pidevspring.Security.payload.request.SignupRequest;
import com.coconsult.pidevspring.Security.payload.response.MessageResponse;
import com.coconsult.pidevspring.Security.payload.response.PasswordGenerator;
import com.coconsult.pidevspring.Services.User.EmailService;

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

import java.util.*;

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
    @Autowired
    private EmailService emailService;

    @GetMapping("/retrieveAllUser")
    public List<User> retrieveAllUser() {
        List<User> users= iUserService.retrieveAllUser();
        return users;

    }
    @GetMapping("/count")
    public Long count() {
        Long users= iUserService.count();
        return users;

    }
    @GetMapping("/roles/count")
    public ResponseEntity<Map<String, Long>> countUsersByRoles() {
        Map<String, Long> roleCounts = iUserService.countUsersByRoles();
        return ResponseEntity.ok(roleCounts);
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
        String newPassword = PasswordGenerator.generateNewPassword();
        user.setPassword(encoder.encode(newPassword));

        user.setImage("user.jpg");
        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByRoleName("Employee")
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
                    case "admin_hr":
                        Role admin_hrRole = roleRepository.findByRoleName("admin_hr")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(admin_hrRole);

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
        emailService.send(user.getEmail(), newPassword);
        iUserService.addUser(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User u) {
        return iUserService.updateUser(u);
    }
    @PutMapping("/updateUsermdp")
    public User updateUserMdp(@RequestBody User u) {
        u.setPassword(encoder.encode(u.getPassword())    );
        return iUserService.updateUsermdp(u);
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
            List<User> usersToSave = new ArrayList<>();
            List<String> existingEmails = new ArrayList<>();

            // Iterate through the list of users to filter out existing emails and generate passwords for new users
            for (User user : users) {
                if (userRepository.existsByEmail(user.getEmail())) {
                    existingEmails.add(user.getEmail()); // Add existing email to the list
                } else {
                    String newPassword = PasswordGenerator.generateNewPassword();
                    user.setImage("user.jpg");
                    user.setPassword(encoder.encode(newPassword)); // Generate password
                    usersToSave.add(user); // Add user to the list to save
                    emailService.send(user.getEmail(), newPassword); // Send email with password


                    // Save roles for the user
                    Set<Role> roles = new HashSet<>();
                    for (Role role : user.getRoles()) {
                        Role existingRole = roleRepository.findByRoleName("Employee")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(existingRole);
                    }
                    user.setRoles(roles);

                }
            }

            if (!usersToSave.isEmpty()) {
                userRepository.saveAll(usersToSave); // Save new users
            }

            if (!existingEmails.isEmpty()) {
                return ResponseEntity.badRequest().body("Error: Email(s) already in use: " + existingEmails);
            }

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


    //haifa ///



    }

