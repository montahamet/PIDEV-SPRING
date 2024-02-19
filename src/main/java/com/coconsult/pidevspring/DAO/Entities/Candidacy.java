package com.coconsult.pidevspring.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    String motivationLetter;
    LocalDateTime submissionDate;
    @Enumerated(EnumType.STRING)
    StatusCandidacy candidacystatus;
    @ManyToOne
    Job_Offer job_offer;
    @ManyToOne(cascade = CascadeType.ALL)
    User user;
}
