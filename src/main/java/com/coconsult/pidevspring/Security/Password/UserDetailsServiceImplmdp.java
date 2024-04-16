package com.coconsult.pidevspring.Security.Password;

import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

@Service
public class UserDetailsServiceImplmdp implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new HashSet<>());
	}

	public User save(UserDTO userDTO) {
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setFirstname(userDTO.getName());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		return userRepository.save(user);
	}

	public String sendEmail(User user) {
		try {
			String resetLink = generateResetToken(user);
			if (!resetLink.isEmpty()) {
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setTo(user.getEmail());
				helper.setSubject("Reset Password");
				helper.setText("Hello,\n\nPlease click on the link below to reset your password:\n" + resetLink
						+ "\n\nRegards,\nYour Company Name");

				javaMailSender.send(message);
				return "success";
			} else {
				return "error";
			}
		} catch (MessagingException e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String generateResetToken(User user) {
		UUID uuid = UUID.randomUUID();
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime expiryDateTime = currentDateTime.plusMinutes(90);
		user.setResetPasswordToken(uuid.toString());
		user.setExpiryDateToken(expiryDateTime);
		userRepository.save(user);
		if (user.getResetPasswordToken() != null) {
			return "http://localhost:4200/reset/" + user.getResetPasswordToken();
		}
		return "";
	}

	public boolean hasExpired(LocalDateTime expiryDateTime) {

		LocalDateTime currentDateTime = LocalDateTime.now();
		return expiryDateTime.isBefore(currentDateTime);
	}

}