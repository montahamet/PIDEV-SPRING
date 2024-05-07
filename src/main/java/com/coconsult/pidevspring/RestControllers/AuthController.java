package com.coconsult.pidevspring.RestControllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
import com.coconsult.pidevspring.DAO.Entities.Role;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.User.RoleRepository;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import com.coconsult.pidevspring.Security.Password.UserDTO;
import com.coconsult.pidevspring.Security.Password.UserDetailsServiceImplmdp;
import com.coconsult.pidevspring.Security.payload.request.NewPasswordRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.coconsult.pidevspring.Security.JWT.JwtUtils;
import com.coconsult.pidevspring.Security.Oauth.UrlDto;
import com.coconsult.pidevspring.Security.payload.request.ForgotPasswordRequest;

import com.coconsult.pidevspring.Security.payload.request.LoginRequest;
import com.coconsult.pidevspring.Security.payload.request.SignupRequest;
import com.coconsult.pidevspring.Security.payload.response.MessageResponse;
import com.coconsult.pidevspring.Security.payload.response.PasswordGenerator;
import com.coconsult.pidevspring.Security.payload.response.UserInfoResponse;
import com.coconsult.pidevspring.Security.Services.UserDetailsImpl;

import com.coconsult.pidevspring.Services.User.EmailService;
import com.coconsult.pidevspring.Services.User.UserService;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//for Angular Client (withCredentials)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
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
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;
    @Value("${coconsult.app.jwtSecret}")
    private String jwtSecret;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(jwtCookie.toString(),
                        userDetails.getId(),
                        userDetails.getEmail(),
                        roles
                ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {


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
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByRoleName("ROLE_USER")
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
                    case "mod":
                        Role modRole = roleRepository.findByRoleName("ROLE_MODERATOR")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByRoleName("ROLE_USER")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        // Check if the email exists in your user database
        String userEmail = request.getEmail();
        User user = new User();
        // Your logic to check if the email exists in your database

        // If the email exists, generate a new password and send it to the user's email
        if (userRepository.existsByEmail(userEmail)) {
            user = userRepository.findUserByEmail(userEmail);
            String newPassword = PasswordGenerator.generateNewPassword();
            // Implement this method to generate a new password
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
            emailService.send(userEmail, newPassword);
            return ResponseEntity.ok("Password reset instructions sent to your email.");
        } else {
            // If the email does not exist, return a not found response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
        }
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
    @GetMapping("/auth/url")
    public ResponseEntity<UrlDto> auth() {
        String url = new GoogleAuthorizationCodeRequestUrl(clientId,
                "http://localhost:4200",
                Arrays.asList(
                        "email",
                        "profile",
                        "openid")).build();
        logger.info("+++++++++");

        return ResponseEntity.ok(new UrlDto(url));

    }

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/auth/callback")
    public ResponseEntity<?> callback(@RequestParam("code") String code) throws URISyntaxException {

        String token;
        try {
            // Exchange authorization code for access token
            GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    new GsonFactory(),
                    clientId,
                    clientSecret,
                    code,
                    "http://localhost:4200")
                    .execute();

            String accessToken = tokenResponse.getAccessToken();

            // Use access token to retrieve user information
            GoogleIdToken idToken = tokenResponse.parseIdToken();
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            logger.info(email);
            User user = userRepository.findUserByEmail(email);
            if (user!= null){
                UserDetailsImpl userDetails = UserDetailsImpl.build(user);

                // You can also retrieve other user information such as name, profile picture, etc. from the payload

                List<String> roles = userDetails.getAuthorities().stream()
                        .map(item -> item.getAuthority())
                        .collect(Collectors.toList());

                return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, accessToken)
                        .body(new UserInfoResponse(accessToken,
                        userDetails.getId(),
                        userDetails.getEmail(),
                        roles
                ));}
            else
                return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            // Handle exceptions
            logger.error("Error exchanging authorization code for token: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
