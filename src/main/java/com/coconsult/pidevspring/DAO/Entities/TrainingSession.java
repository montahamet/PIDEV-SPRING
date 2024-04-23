package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingSession implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ts_id ;
    String title;
    LocalDateTime  start_date;
    LocalDateTime finish_date;
     String topic;
     String place;
    long capacity;
    @Enumerated(EnumType.STRING)
    TypeTS typeTS;
    @Enumerated(EnumType.STRING)
    TS_Status tsStatus;
    @JsonIgnore
    @ManyToOne
    User user;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<RegistrationTS> registrationtss;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="trainingsession")
    private Set<FeedBack> FeedBacks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id") // Adjust column name as necessary
    @JsonInclude(JsonInclude.Include.NON_NULL) // Include room details in the JSON unless null
    private Room room;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlaceType placeType;
}
