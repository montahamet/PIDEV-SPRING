package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Room;

import java.util.List;
import java.util.Optional;

public interface IRoomService {

    public List<Room> getAllRooms() ;

    public Optional<Room> getRoomById(Long id) ;

    public Room saveRoom(Room room) ;
    public Room updateRoom(Long id, Room roomDetails);

    public void deleteRoom(Long id) ;
    }
