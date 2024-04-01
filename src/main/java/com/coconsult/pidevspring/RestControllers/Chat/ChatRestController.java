package com.coconsult.pidevspring.RestControllers.Chat;

import com.coconsult.pidevspring.DAO.Entities.Chat;
import com.coconsult.pidevspring.Services.Chat.IChatService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("/chat")
public class ChatRestController {

    IChatService iChatService;
    @GetMapping("/retrieveAllChat")
    public List<Chat> retrieveAllChat() {
        List<Chat> users= iChatService.retrieveAllChats();
        return users;
    }

    @PostMapping("/addChat")
    public Chat addChat(@RequestBody Chat c) {
        return iChatService.addChat(c);
    }

    @PutMapping("/updateChat")
    public Chat updateChat(@RequestBody Chat c) {
        return iChatService.updateChat(c);
    }

    @GetMapping("/retrieveOneChat")
    public Chat retrieveOneChat(@PathParam("chatId") Long chatId) {
        return iChatService.retrieveOneChat(chatId);
    }

    @DeleteMapping("/removeChat")
    public void removeChat(@PathParam("chatId") Long chatId) {
        iChatService.removeChat(chatId);

    }
}
