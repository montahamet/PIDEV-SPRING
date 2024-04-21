package com.coconsult.pidevspring.Services.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Room;
import com.coconsult.pidevspring.DAO.Entities.TrainingSession;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.RoomRepository;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.TrainingSessionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RoomService implements IRoomService {
    private final RoomRepository roomRepository;
    private final TrainingSessionRepository trainingSessionRepository;  // Added repository for TrainingSession

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    @Transactional
    public Room saveRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        return roomRepository.save(room);  // This should return a Room object
    }


    @Override
    public Room updateRoom(Long id, Room roomDetails) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Room with ID " + id + " not found"));
        room.setNameRoom(roomDetails.getNameRoom());
        room.setCapacityRoom(roomDetails.getCapacityRoom());
        room.setAvailable(roomDetails.isAvailable());
        room.setEquipmentR(roomDetails.getEquipmentR());
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Room with ID " + id + " not found"));
        roomRepository.delete(room);
    }
}
