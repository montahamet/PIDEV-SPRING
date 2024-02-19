package com.coconsult.pidevspring.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Job_Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long jobOffer_id ;
    String title;
    LocalDateTime postedDate;
    String Description;
    String requiredSkills;
    Integer nbPlaces;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="job_offer")
    private Set<Candidacy> Candidacys;
    @ManyToOne
    User user;
}
