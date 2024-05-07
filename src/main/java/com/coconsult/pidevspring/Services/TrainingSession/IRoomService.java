package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Room;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IRoomService {

    public Page<Room> getAllRooms(Pageable pageable) ;

    public Optional<Room> getRoomById(Long id) ;

    public Room saveRoom(Room room) ;
    public Room updateRoom(Long id, Room roomDetails);

    public void deleteRoom(Long id) ;
    public List<Date> getUnavailableDatesForRoom(Long roomId) ;


    public boolean isRoomAvailable(Long roomId, LocalDateTime startDate, LocalDateTime endDate) ;
    }
