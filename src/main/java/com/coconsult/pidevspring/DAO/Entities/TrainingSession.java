package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class TrainingSession implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long TS_id ;
    String title;
    LocalDateTime  start_Date;
    LocalDateTime Finish_Date;
    String Topic;
    long Capacity;
    String Place;
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



}
