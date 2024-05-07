package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Leaves implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long leaveId ;
     String duration ;
     boolean approved ;
     String reason ;
    LocalDate startdate ;
    LocalDate enddate ;
    boolean archive ;

    @JsonIgnore
    @ManyToOne
    private User employee;
    @JsonIgnore
    @ManyToOne
    private User admin;


}
