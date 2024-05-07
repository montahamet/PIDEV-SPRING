package com.coconsult.pidevspring.DAO.Repository.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Room;
import com.coconsult.pidevspring.DAO.Entities.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room> findByAvailable(boolean isAvailable);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Room r " +
            "LEFT JOIN r.trainingSessions bd " +
            "WHERE r.id = :roomId " +
            "AND (:startDate NOT BETWEEN bd AND bd.start_date " +
            "AND :endDate NOT BETWEEN bd AND bd.finish_date)")
    boolean isRoomAvailable(
            @Param("roomId") Long roomId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
    @Query("SELECT ts.start_date FROM TrainingSession ts WHERE ts.room.id = :roomId AND " +
            "((ts.start_date BETWEEN :startDate AND :endDate) OR " +
            "(ts.finish_date BETWEEN :startDate AND :endDate) OR " +
            "(ts.start_date <= :startDate AND ts.finish_date >= :endDate))")
    List<LocalDateTime> findOverlappingDates(@Param("roomId") Long roomId,
                                             @Param("startDate") LocalDateTime startDate,
                                             @Param("endDate") LocalDateTime endDate);
}

