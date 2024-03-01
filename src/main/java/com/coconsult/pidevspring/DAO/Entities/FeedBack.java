package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeedBack  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long feedback_id ;

    String description ;
    LocalTime FeedBack_date ;
    int note ;
@JsonIgnore
    @ManyToOne
    User user;
    @JsonIgnore

    @ManyToOne
    Event event;
    @JsonIgnore

    @ManyToOne
    TrainingSession trainingsession;

}
