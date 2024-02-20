package com.coconsult.pidevspring.DAO.Repositories.Chat;

import com.coconsult.pidevspring.DAO.Entities.Chat;
import com.coconsult.pidevspring.DAO.Entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
