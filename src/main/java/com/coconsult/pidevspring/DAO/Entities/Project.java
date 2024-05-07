package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long projectId;
    String projectName;
    String projectDescription;
    LocalDate startdateProject;
    LocalDate enddateProject;
    String fileName;
    float  progressp;

    @Enumerated(EnumType.STRING)
    StatusProject projectStatus;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "projetT")
    @JsonIgnore
    List<Task> tasks=new ArrayList<>();
    @OneToOne
    @JsonIgnore
    Contract contract;
    @OneToOne
    @JsonIgnore
    Documentation document;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "projetInvoice")
    List<Invoice> invoices=new ArrayList<>();
//    @JsonIgnore
    @ManyToOne


    User projectManager;
    @ManyToOne
    @JsonIgnore
    ProjectOffer projectoffer;

}
