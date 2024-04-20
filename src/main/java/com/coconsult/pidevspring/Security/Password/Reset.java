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

    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        return "forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String forgotPassordProcess(@RequestBody ForgotPasswordRequest userDTO) {
        String output = "";
        logger.info("3333333");
        logger.info(userDTO.getEmail()+"------");
        User user = userRepository.findUserByEmail(userDTO.getEmail());
        if (user != null) {
            logger.info(user.getEmail());
            output = userDetailsService.sendEmail(user);
        }
        if (output.equals("success")) {
            return "redirect:/api/auth/forgotPassword?success";
        }
        return "redirect:/login?error";
    }

    @GetMapping("/resetPassword/{token}")
    public String resetPasswordForm(@PathVariable String token, Model model) {
        logger.info("1111111111111111111");
        User reset = userRepository.findUserByResetPasswordToken(token);


        if (reset != null && userDetailsService.hasExpired(reset.getExpiryDateToken())) {
            logger.info("////////////////////////////");
            model.addAttribute("email", reset.getEmail());
            return "reset";
        }
        logger.info("66666666666");
        return "redirect:/forgotPassword?error";
    }
    @PutMapping("/resetPassword/{token}")
    public String passwordResetProcess(@PathVariable String token,@RequestBody NewPasswordRequest request) {
        logger.info("***"+request.getToken()+"+++");
        String t = request.getToken();
        User reset = userRepository.findUserByResetPasswordToken(t);
        logger.info("11"+reset.getPassword()+"+++");
        logger.info("111"+request.getToken()+"+++");
        User user = userRepository.findUserByEmail("thamersouelmi10@gmail.com");
        logger.info("***"+user+"+++");
        logger.info("***"+request.getPassword()+"+++");
        if (reset != null ) {
            reset.setPassword(encoder.encode(request.getPassword()));
            logger.info("000"+reset.getPassword());
            userRepository.save(reset);
            return "redirect:/login";
        }
        return null;
    }

}
