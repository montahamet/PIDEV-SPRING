package com.coconsult.pidevspring.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long event_id ;

    String Event_name;
    LocalDateTime Event_date;

    @ManyToOne
    User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="event")
    private Set<RegistationEvent> RegistationEvents;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="event")
    private Set<Activity> Activitys;
}
