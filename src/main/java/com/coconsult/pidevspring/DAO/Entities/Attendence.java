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
    private Long attendenceId ;
    private boolean presence;
    private boolean approved ;
    private String reason ;
    private LocalDateTime date ;
    @Enumerated(EnumType.STRING)
    private TypeAttendence typeAttendence ;
    @JsonIgnore
    @ManyToOne
    private User employee;
    @JsonIgnore
    @ManyToOne
    private User admin;


}
