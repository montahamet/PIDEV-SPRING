package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long taskid;
    String taskname;
    LocalDate startDateTask;
    LocalDate dueDateTask;
    String taskDescription;
    float  progress;
    long duration;
    long parent;
    @Enumerated(EnumType.STRING)
    Priority priority;
    @Enumerated(EnumType.ORDINAL)
    StatusTask taskStatus;
    @JsonIgnore
    @ManyToOne
    Project projetT;
//    @JsonIgnore
    @ManyToOne
    User employeeTask;





}