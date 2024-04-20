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
public class RegistrationTS implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long registrationTS_id ;
    @Enumerated(EnumType.STRING)
    Status RegistrationTS_status ;
    LocalDateTime RegistrationTS_date;
    @ManyToOne
    User user;


}
