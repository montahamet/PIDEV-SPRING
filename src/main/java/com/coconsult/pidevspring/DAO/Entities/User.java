package com.coconsult.pidevspring.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Collection;
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
    Long id ;
    @Column(unique = true)
    String email;
    String firstname;
    String lastname;
    String password;
    String Adresse;
    /////////////////////// haifa
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<FeedBack> FeedBacks;
    /////////////////////// haifa
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<RegistationEvent> RegistationEvents;
    /////////////////////// haifa
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Event> Events;
    /////////////////////// haifa
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<RegistationTS> RegistationTSs;
    /////////////////////// haifa
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<TrainingSession> TrainingSessions;
}

