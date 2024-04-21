package com.coconsult.pidevspring.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.io.Serializable;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(nullable = false)
    boolean available;
    @ElementCollection
    @CollectionTable(name = "room_equipment", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "equipmentr")
    List<String> equipmentR;

    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST, orphanRemoval = true) // Changed CascadeType
    private Set<TrainingSession> trainingSessions = new HashSet<>();

}
