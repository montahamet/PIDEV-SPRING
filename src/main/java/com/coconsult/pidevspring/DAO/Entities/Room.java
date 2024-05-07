package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name_room", nullable = false)
    String nameRoom;

    @Column(name = "capacity_room", nullable = false)
    int capacityRoom;

    @ElementCollection
    @CollectionTable(name = "room_booking_dates", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "booking_date")
    List<Date> bookingDates;

    @Column(nullable = false)
    boolean available;

    @ElementCollection
    @CollectionTable(name = "room_equipment", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "equipmentr")
    List<String> equipmentR;

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    private List<TrainingSession> trainingSessions;
}
