package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.*;
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
@JsonIgnoreProperties(ignoreUnknown = true)

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingSession implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ts_id ;
    String title;
    String target_audience;
    String session_outline;
    String expected_outcomes;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime start_date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime finish_date;
     String topic;
    @Column(nullable = true)
     String place;
    int capacity;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User trainer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "room_id", nullable = true)
    private Room room;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private PlaceType placeType;
}
