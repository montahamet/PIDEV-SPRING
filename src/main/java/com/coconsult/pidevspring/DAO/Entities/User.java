package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements   Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_user ;
    @Column(unique = true)
    String email;
    String firstname;
    String lastname;
    String password;
    String Adresse;
    @Enumerated(EnumType.STRING)
    Gender gender;
    /////////////////////// Malek //////////////////////
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "projectManager")
    List<Project> projects=new ArrayList<>();
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "employeeTask")
    List<Task> employeeTask=new ArrayList<>();
    /////////////////////// Malek //////////////////////


    /////////////////////// haifa //////////////////////
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<FeedBack> FeedBacks;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<RegistationEvent> RegistationEvents;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Event> Events;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<RegistationTS> RegistationTSs;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<TrainingSession> TrainingSessions;
    /////////////////////// haifa //////////////////////
    /////////////////////// montaha //////////////////////
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<JobOffer> Job_Offers;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="userHR")
    private Set<Interview> InterviewsHR;//montaha
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="userCANDIDATE")
    private Set<Interview> InterviewsCandidate;//montaha
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<ProjectOffer> ProjectOffers;//montaha
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="userEvaluator")
    private Set<PerformanceEvaluation> PerformanceEvaluations;//montaha
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="userEvaluated")
    private Set<PerformanceEvaluation> EmployeePerformanceEvaluations;//montaha
    /////////////////////// Montaha //////////////////////


}

