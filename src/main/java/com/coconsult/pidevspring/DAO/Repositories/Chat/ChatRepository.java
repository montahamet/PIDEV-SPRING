package com.coconsult.pidevspring.DAO.Repositories.Chat;

import com.coconsult.pidevspring.DAO.Entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {
}
