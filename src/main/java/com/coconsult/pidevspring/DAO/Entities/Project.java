package com.coconsult.pidevspring.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
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
    long project_id;
    String project_name;
    String projectDescription;
    LocalDate startdateProject;
    LocalDate enddateProject;
    @Enumerated(EnumType.STRING)
    StatusProject projectStatus;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "projetT")
    List<Task> tasks=new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "projetContract")
    List<Contract> contracts=new ArrayList<>();
    @OneToOne
    Documentation document;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "projetInvoice")
    List<Invoice> invoices=new ArrayList<>();
    @ManyToOne
    User projectManager;

}
