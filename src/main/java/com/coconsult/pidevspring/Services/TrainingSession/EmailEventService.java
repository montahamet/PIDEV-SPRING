package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.Services.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailEventService implements IEmailEventService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    public void sendEventConfirmationEmail(Long userId, String eventName) {
        User user = userService.retrieveOneUser(userId);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("votre.email@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Confirmation de participation à l'événement");
        message.setText("Cher(e) " + user.getFirstname() + ",\n\nVous avez confirmé votre participation à l'événement: " + eventName + ".\n\nCordialement,\nL'équipe");

        mailSender.send(message);
    }
}
