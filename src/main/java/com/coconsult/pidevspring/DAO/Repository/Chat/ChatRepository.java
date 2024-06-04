package com.coconsult.pidevspring.DAO.Repository.Chat;

import com.coconsult.pidevspring.DAO.Entities.Chat;
import com.coconsult.pidevspring.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Long> {

    Chat findChatByName(String chat);


Chat findChatByChatId(Long id);
    List<Chat> findByUsersFirstname(String userId);

}
