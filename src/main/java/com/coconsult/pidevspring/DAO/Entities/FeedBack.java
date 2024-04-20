package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @CreationTimestamp
    LocalDateTime feedBack_date;
    int note ;
@JsonIgnore
    @ManyToOne
    User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event;
    @JsonIgnore

    @ManyToOne
    TrainingSession trainingsession;

}
