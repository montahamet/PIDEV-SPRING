package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Attendence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendenceId ;
    private boolean presence;
    private boolean approved ;
    private String reason ;
    private LocalDateTime date ;
    private String typeAttendence ;
    @JsonIgnore
    @ManyToOne
    private User employee;
    @JsonIgnore
    @ManyToOne
    private User admin;


}
