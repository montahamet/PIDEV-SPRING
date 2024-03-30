package com.coconsult.pidevspring.DAO.Repository.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Entities.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface FeedBackRepository extends JpaRepository<FeedBack,Long> {
    @Query("SELECT fb FROM FeedBack fb JOIN fb.event e WHERE e.eventId = :eventId")
    List<FeedBack> findAllByEventEventId(Long eventId);
    @Query("SELECT new map(f.event.id as eventId, AVG(f.note) as averageRating) FROM FeedBack f GROUP BY f.event.id")
    List<Map<String, Object>> findAverageRatingsForAllEvents();

}
