package com.coconsult.pidevspring.Services.Chat;

import com.coconsult.pidevspring.DAO.Entities.Chat;
import com.coconsult.pidevspring.DAO.Repository.Chat.ChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatService implements IChatService {

    ChatRepository chatRepository ;
    @Override
    public List<Chat> retrieveAllChats() {
        return chatRepository.findAll();
    }
    @Override
    public Chat addChat(Chat u) {

        return chatRepository.save(u);
    }

    @Override
    public Chat updateChat(Chat u) {
        return chatRepository.save(u);
    }

    @Override
    public Chat retrieveOneChat(Long ChatId) {
        return chatRepository.findById(ChatId).get();
    }

    @Override
    public void removeChat(Long ChatID) {
        chatRepository.deleteById(ChatID);

    }
}
