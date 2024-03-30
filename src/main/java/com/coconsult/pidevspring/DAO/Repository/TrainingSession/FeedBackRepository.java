package com.coconsult.pidevspring.DAO.Repository.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Entities.FeedBack;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface FeedBackRepository extends JpaRepository<FeedBack,Long> {
    @Query("SELECT fb FROM FeedBack fb JOIN fb.event e WHERE e.eventId = :eventId")
    List<FeedBack> findAllByEventEventId(Long eventId);
    @Query("SELECT AVG(f.note) FROM FeedBack f WHERE f.event.eventId = :eventId")
    Double findAverageRatingByEventId(Long eventId);
    @Modifying
    @Transactional
    @Query("DELETE FROM FeedBack re WHERE re.event.eventId = :eventId")
    void deleteByEventId(@Param("eventId") Long eventId);

}
