package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
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
    LocalDateTime postedDate = LocalDateTime.now();
    String description;
    String requiredSkills;
    Integer vacancy;

    double minsalary;
    double maxsalary;

    @Enumerated(EnumType.STRING)
    JobNature jobNature;
    @Enumerated(EnumType.STRING)
    JobCategory jobCategory;


    ///Relations
    // JobOffer class
//    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL)
    private List<Candidacy> candidacies = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    User user;


    ///Enum
    public enum JobNature{
        FULL_TIME,PART_TIME,INTERN
    }
    public enum JobCategory{
        SoftwareDevelopment,DataScience,Security,InfrastructureNetworking,WebDevelopment,Design,ProjectManagement,BusinessAnalysis,ConsultingAndSales
    }
}
