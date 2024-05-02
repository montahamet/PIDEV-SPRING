package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.Room;
import com.coconsult.pidevspring.DAO.Repository.TrainingSession.RoomRepository;
import com.coconsult.pidevspring.Services.TrainingSession.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@AllArgsConstructor
@RequestMapping("/TrainingSession-Room")
public class RoomRestController {
    private final RoomService roomService;
    RoomRepository roomRepository;
    private static final Logger logger = LoggerFactory.getLogger(RoomRestController.class);



    @GetMapping("/getAll")
    public Page<Room> getAllRooms(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return roomService.getAllRooms((Pageable) PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<?> createRoom(@RequestBody Room roomDto) {
        logger.info("Received room creation request: {}", roomDto);
        try {
            if (roomDto.getCapacityRoom() <= 0) {
                return ResponseEntity.badRequest().body("Capacity must be greater than 0");
            }
            Room savedRoom = roomService.saveRoom(roomDto);
            return ResponseEntity.ok(savedRoom);
        } catch (Exception e) {
            logger.error("Error creating room: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating room: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
        try {
            Room updatedRoom = roomService.updateRoom(id, roomDetails);
            return ResponseEntity.ok(updatedRoom);
        } catch (DataAccessException e) {
            logger.error("Database error during the update of room: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid input data: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (EntityNotFoundException e) {
            logger.error("Room not found: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found");
        } catch (Exception e) {
            logger.error("Failed to update room: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("Failed to update room: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        try {
            roomService.deleteRoom(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Failed to delete room: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("Failed to delete room: " + e.getMessage());
        }
    }

//    @GetMapping("/{roomId}/availability")
//    public ResponseEntity<Boolean> checkRoomAvailability(@PathVariable Long roomId,
//                                                         @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
//                                                         @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
//        boolean isAvailable = roomService.isRoomAvailable(roomId, start, end);
//        return ResponseEntity.ok(isAvailable);
//    }
//    @GetMapping("/check-room-availability/{roomId}")
//    public ResponseEntity<Boolean> checkRoomAvailability(
//            @PathVariable Long roomId,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
//
//        // Convert LocalDateTime to Date
//        Date startDate = Date.from(start.atZone(ZoneId.systemDefault()).toInstant());
//        Date endDate = Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
//
//        // Check room availability for the given date range
//        boolean isAvailable = roomRepository.isRoomAvailable(roomId, startDate, endDate);
//
//        return ResponseEntity.ok(isAvailable);
//    }
//

}
