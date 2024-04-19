package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("Finish_Date")

    LocalDateTime finish_Date;
    @JsonProperty("Topic")
     String topic;

    @JsonProperty("Place")
     String place;
    @JsonProperty("Capacity")

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



}
