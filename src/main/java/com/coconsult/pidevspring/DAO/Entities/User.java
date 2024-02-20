package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
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
    Long userId ;
    @Column(unique = true)
    String email;
    String firstname;
    String lastname;
    String password;
    String Adresse;
    @Enumerated(EnumType.STRING)
    Gender gender;
    /////////////////////// Thamer /////////////////////
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Role> Roles = new HashSet<>();

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Chat> chats = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Message> Messages = new HashSet<>();

    /////////////////////// Thamer /////////////////////
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
    private Set<RegistrationEvent> RegistationEvents;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Event> Events;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<RegistrationTS> RegistationTSs;
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
    private Set<Interview> InterviewsHR;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="userCANDIDATE")
    private Set<Interview> InterviewsCandidate;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<ProjectOffer> ProjectOffers;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="userEvaluator")
    private Set<PerformanceEvaluation> PerformanceEvaluations;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="userEvaluated")
    private Set<PerformanceEvaluation> EmployeePerformanceEvaluations;
    /////////////////////// Montaha //////////////////////
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="employee")
    private Set<Attendence> attendences = new HashSet<>();
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="admin")
    private Set<Attendence> Attendence = new HashSet<>();
}

