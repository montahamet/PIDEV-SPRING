package com.coconsult.pidevspring.Services.Chat;

import com.coconsult.pidevspring.DAO.Entities.Message;
import com.coconsult.pidevspring.DAO.Repositories.Chat.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService implements IMessageService {

    MessageRepository messageRepository ;
    @Override
    public List<Message> retrieveAllMessages() {
        return messageRepository.findAll();
    }
    @Override
    public Message addMessage(Message u) {
        return messageRepository.save(u);
    }

    @Override
    public Message updateMessage(Message u) {
        return messageRepository.save(u);
    }

    @Override
    public Message retrieveOneMessage(Long messageId) {
        return messageRepository.findById(messageId).get();
    }

    @Override
    public void removeMessage(Long messageID) {
        messageRepository.deleteById(messageID);

    }
}
