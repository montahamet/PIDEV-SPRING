package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobOffer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long jobOffer_id ;
    String titleJobOffer;
    String jobLocation;
    LocalDateTime applicationDeadLine;
    String experience;
    LocalDateTime postedDate;
    String jobLocation;
    LocalDateTime applicationDeadLine;
    String experience;
    String description;
    String requiredSkills;

    Integer vacancy;

    double salary;
    @Enumerated(EnumType.STRING)
    JobNature jobNature;


    ///Relations

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="job_offer")
    private Set<Candidacy> Candidacys;
    @JsonIgnore
    @ManyToOne
    User user;


    ///Enum
    public enum JobNature{
        FULL_TIME,PART_TIME,INTERN
    }
}
