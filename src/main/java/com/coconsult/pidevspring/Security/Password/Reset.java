package com.coconsult.pidevspring.Security.Password;

import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.User.RoleRepository;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import com.coconsult.pidevspring.RestControllers.AuthController;
import com.coconsult.pidevspring.Security.JWT.JwtUtils;
import com.coconsult.pidevspring.Security.payload.request.ForgotPasswordRequest;
import com.coconsult.pidevspring.Security.payload.request.NewPasswordRequest;
import com.coconsult.pidevspring.Services.User.EmailService;
import com.coconsult.pidevspring.Services.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class Reset {
    private static final Logger logger = LoggerFactory.getLogger(Reset.class);
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserDetailsServiceImplmdp userDetailsService;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

 /*   @GetMapping("/forgotPassword")
    public String forgotPassword() {
        return "forgotPassword";
    }*/

    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassordProcess(@RequestBody ForgotPasswordRequest userDTO) {
        String output = "";
        logger.info("3333333");
        logger.info(userDTO.getEmail()+"------");
        User user = userRepository.findUserByEmail(userDTO.getEmail());
        if (user != null) {
            logger.info(user.getEmail());
            output = userDetailsService.sendEmail(user);
        }
        if (output.equals("success")) {
            return ResponseEntity.ok("Password reset instructions sent to your email.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
    }

    @GetMapping("/resetPassword/{token}")
    public ResponseEntity<String> resetPasswordForm(@PathVariable String token, Model model) {
        logger.info("1111111111111111111");
        User reset = userRepository.findUserByResetPasswordToken(token);


        if (reset != null && userDetailsService.hasExpired(reset.getExpiryDateToken())) {
            logger.info("////////////////////////////");
            model.addAttribute("email", reset.getEmail());
            return ResponseEntity.ok("Password reset instructions sent to your email.");
        }
        logger.info("66666666666");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
    }
    @PutMapping("/resetPassword/{token}")
    public ResponseEntity<String> passwordResetProcess(@PathVariable String token,@RequestBody NewPasswordRequest request) {

        User reset = userRepository.findUserByResetPasswordToken(token);


        if (reset != null ) {
            reset.setPassword(encoder.encode(request.getPassword()));
            logger.info("000"+reset.getPassword());
            userRepository.save(reset);
            return ResponseEntity.ok("Password reset instructions sent to your email.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
    }

}
