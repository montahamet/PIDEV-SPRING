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
public class Candidacy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long candidacy_id ;
    String cv;
    String coverLetter;
    LocalDateTime submissionDate;
    @Enumerated(EnumType.STRING)
    StatusCandidacy candidacystatus;
    @JsonIgnore
    @ManyToOne
    JobOffer job_offer;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    User user;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="candidacy")
    private Set<Interview> Interviews;
}
