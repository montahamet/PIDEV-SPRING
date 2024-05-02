package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class    User implements   Serializable , UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId ;
    @Column(unique = true)
    String email;
    String firstname;
    String lastname;
    String password;
    String Adresse;
    LocalDate birthdate ;
    Integer phonenumber ;
    @Enumerated(EnumType.STRING)
    Gender gender;
    String image ;
    Boolean locked = true;
    Boolean enabled = true;

    String resetPasswordToken;
    private LocalDateTime expiryDateToken;
    /////////////////////// Thamer /////////////////////

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

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
    List<Task> employeeTasks=new ArrayList<>();
    /////////////////////// Malek //////////////////////


    /////////////////////// haifa //////////////////////
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<FeedBack> FeedBacks;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<RegistrationEvent> RegistationEvents;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Event> Events = new HashSet<>();
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<TrainingSession> trainingSessions = new HashSet<>();
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<RegistrationEvent> RegistationTSs;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<TrainingSession> TrainingSessions;
    /////////////////////// haifa //////////////////////
    /////////////////////// montaha //////////////////////
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<JobOffer> Job_Offers;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Interview> InterviewsHR;
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

    public User(String email, String encode) {
    }

    public User(String encode) {
    }

    public User(String email, String firstname, String lastname, LocalDate birthdate, Gender gender, Integer phonenumber, String adresse, String encode) {
    }


    /////////////////////////////////CONFIG////////////////////

   @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

