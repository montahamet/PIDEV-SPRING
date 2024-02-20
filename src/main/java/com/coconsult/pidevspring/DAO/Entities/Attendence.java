package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Attendence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long attendenceId ;
    boolean presence;
    boolean approved ;
    String reason ;
    LocalDateTime date ;
    @Enumerated(EnumType.STRING)
    TypeAttendence typeAttendence ;
    @JsonIgnore
    @ManyToOne
    User employee;
    @JsonIgnore
    @ManyToOne
    User admin;


}
