package com.coconsult.pidevspring.Services.Chat;

import com.coconsult.pidevspring.DAO.Entities.Chat;

import java.util.List;

public interface IChatService {
    List<Chat> retrieveAllChats();

    Chat addChat(Chat r);

    Chat updateChat(Chat r);

    Chat retrieveOneChat(Long chatId);

    void removeChat(Long chatId);
}
