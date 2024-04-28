package com.coconsult.pidevspring.DAO.Repository.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Activity a WHERE a.event.eventId = :eventId")
    boolean existsByEventId(Long eventId);
//    boolean existsByEventIdAndUserId(Long eventId, Long userId);
@Query("SELECT e FROM Event e WHERE e.event_date >= ?1")
List<Event> findUpcomingEvents(LocalDate today);
    @Query("SELECT e FROM Event e WHERE e.event_date > :date")
    Page<Event> findAllWithDateAfter(@Param("date") LocalDate date, Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.event_date >= :start AND e.finishevent_date <= :end")
    List<Event> findEventsBetweenDates(LocalDate start, LocalDate end);
}
