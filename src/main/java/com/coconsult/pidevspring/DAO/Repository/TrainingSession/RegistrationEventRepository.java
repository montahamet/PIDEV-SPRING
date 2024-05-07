package com.coconsult.pidevspring.DAO.Repository.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Entities.RegistrationEvent;
import com.coconsult.pidevspring.DAO.Entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RegistrationEventRepository extends JpaRepository<RegistrationEvent, Long> {
    Set<RegistrationEvent> findByEvent(Event event);
    @Modifying
    @Transactional
    @Query("DELETE FROM RegistrationEvent re WHERE re.event.eventId = :eventId")
    void deleteByEventId(@Param("eventId") Long eventId);
    boolean existsByEvent_EventIdAndUser_UserId(Long eventId, Long userId);
    @Query("SELECT re.user FROM RegistrationEvent re WHERE re.event.eventId = :eventId")
    List<User> findUsersByEventId(Long eventId);
    @Query("SELECT re FROM RegistrationEvent re WHERE re.event.eventId = :eventId AND re.user.userId = :userId")
    Optional<RegistrationEvent> findByEventIdAndUserId(Long eventId, Long userId);

    List<RegistrationEvent> findByEventEventId(Long eventId);

}
