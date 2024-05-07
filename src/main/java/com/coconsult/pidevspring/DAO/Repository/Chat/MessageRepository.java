package com.coconsult.pidevspring.DAO.Repository.Chat;

import com.coconsult.pidevspring.DAO.Entities.Chat;
import com.coconsult.pidevspring.DAO.Entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findAllByChatChatId(Long id);
}
