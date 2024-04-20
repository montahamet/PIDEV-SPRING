package com.coconsult.pidevspring.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationEvent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long registrationE_id ;

    @Enumerated(EnumType.STRING)
    Status RegistrationEvent_status;

    LocalDateTime registration_date;

    @ManyToOne
    User user;


    @ManyToOne
    Event event;
}
