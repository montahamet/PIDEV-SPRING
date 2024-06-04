package com.coconsult.pidevspring.RestControllers.Chat;

import com.coconsult.pidevspring.DAO.Entities.Chat;
import com.coconsult.pidevspring.DAO.Entities.Message;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.Chat.ChatRepository;
import com.coconsult.pidevspring.DAO.Repository.Chat.MessageRepository;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import com.coconsult.pidevspring.RestControllers.AuthController;
import com.coconsult.pidevspring.Security.payload.request.MessageDto;
import com.coconsult.pidevspring.Services.Chat.IChatService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

public class ChatRestController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private ChatRepository chatDAO;
    @Autowired
    private MessageRepository messageDAO;
    @Autowired
    private UserRepository userRepository;
    @MessageMapping("/chat/{to}") //to = nome canale
    public void sendMessage(@DestinationVariable String to , Message message) {
        System.out.println("handling send message: " + message + " to: " + to);

        Long id =      (createAndOrGetChat(to));
        Chat c = chatDAO.findChatByChatId(id);
        message.setChat(c);
        message.setT_stamp(generateTimeStamp());
        message = messageDAO.save(message);
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
    }

    @PostMapping("/getChats")
    public List<Chat> getChats(@RequestBody String user){
        return chatDAO.findByUsersFirstname(user);
    }

    //returns an empty list if the chat doesn't exist
    @PostMapping("/getMessages")
    public List<Message> getMessages(@RequestBody String chat) {
        Chat ce = chatDAO.findChatByName(chat);

        if(ce != null) {
            return messageDAO.findAllByChatChatId(ce.getChatId());
        }
        else{
            return new ArrayList<Message>();
        }
    }

    //finds the chat whose name is the parameter, if it doesn't exist it gets created, the ID gets returned either way
    private Long createAndOrGetChat(String name) {
        Chat ce = chatDAO.findChatByName(name);

        if (ce != null) {
            return ce.getChatId();
        }
        else {
            Chat newChat = new Chat(name);
            return chatDAO.save(newChat).getChatId();
        }
    }

    private String generateTimeStamp() {
        Instant i = Instant.now();
        String date = i.toString();
        System.out.println("Source: " + i.toString());
        int endRange = date.indexOf('T');
        date = date.substring(0, endRange);
        date = date.replace('-', '/');
        System.out.println("Date extracted: " + date);
        String time = Integer.toString(i.atZone(ZoneOffset.UTC).getHour() + 1);
        time += ":";

        int minutes = i.atZone(ZoneOffset.UTC).getMinute();
        if (minutes > 9) {
            time += Integer.toString(minutes);
        } else {
            time += "0" + Integer.toString(minutes);
        }

        System.out.println("Time extracted: " + time);
        String timeStamp = date + "-" + time;
        return timeStamp;
    }
}
