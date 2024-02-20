package com.coconsult.pidevspring.Services.Chat;

import com.coconsult.pidevspring.DAO.Entities.Message;

import java.util.List;

public interface IMessageService {
    List<Message> retrieveAllMessages();

    Message addMessage(Message r);

    Message updateMessage(Message r);

    Message retrieveOneMessage(Long messageId);

    void removeMessage(Long messageId);
}
