package com.coconsult.pidevspring.RestControllers.Chat;

import com.coconsult.pidevspring.DAO.Entities.Message;
import com.coconsult.pidevspring.Services.Chat.IMessageService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("/message")
public class MessageRestController {

    IMessageService iMessageService;
    @GetMapping("/retrieveAllMessages")
    public List<Message> retrieveAllMessages() {
        List<Message> messages= iMessageService.retrieveAllMessages();
        return messages;
    }

    @PostMapping("/addMessage")
    public Message addMessage(@RequestBody Message m) {
        return iMessageService.addMessage(m);
    }

    @PutMapping("/updateMessage")
    public Message updateMessage(@RequestBody Message m) {
        return iMessageService.updateMessage(m);
    }

    @GetMapping("/retrieveOneMessage")
    public Message retrieveOneUser(@PathParam("messageId") Long messageId) {
        return iMessageService.retrieveOneMessage(messageId);
    }

    @DeleteMapping("/removeMessage")
    public void removeUser(@PathParam("messageId") Long messageId) {
        iMessageService.removeMessage(messageId);

    }
}
