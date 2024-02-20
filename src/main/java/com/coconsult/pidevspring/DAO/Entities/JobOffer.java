package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class JobOffer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long jobOffer_id ;
    String titleJobOffer;
    LocalDateTime postedDate;
    String Description;
    String requiredSkills;
    Integer nbPlaces;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="job_offer")
    private Set<Candidacy> Candidacys;
    @JsonIgnore
    @ManyToOne
    User user;
}
