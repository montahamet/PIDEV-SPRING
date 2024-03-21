package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long event_id ;

    String event_name;
    LocalDate event_date;
    String event_description;
    String place;


    @ManyToMany(mappedBy="Events", cascade = CascadeType.ALL)
    private Set<User> users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="event")
    private Set<RegistrationEvent> RegistationEvents;
@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="event")
    private Set<Activity> Activitys;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="event")
    private Set<FeedBack> FeedBacks;


}
