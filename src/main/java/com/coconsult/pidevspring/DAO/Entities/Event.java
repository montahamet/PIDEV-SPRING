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
    Long eventId ;

    String event_name;
    LocalDateTime event_date;
    String event_description;
    String place;
    Double latitude;
    Double longitude;
   double averageRating;

@JsonIgnore

    @ManyToMany(mappedBy="Events", cascade = CascadeType.ALL)
    private Set<User> users;
@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="event")
    private Set<RegistrationEvent> RegistationEvents;
@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="event")
    private Set<Activity> Activitys;
@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="event")
    private Set<FeedBack> FeedBacks;
//    @JsonIgnore
//    @ManyToMany(mappedBy = "likedEvents", cascade = CascadeType.ALL)
//    private Set<User> likedByUsers;

}
