package com.coconsult.pidevspring.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectOffer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long offer_id ;
    String ProjectTitle;
    String description;
    LocalDate postedDate;
    @Enumerated(EnumType.STRING)
    ProjectOfferStatus status_offer;
    @ManyToOne
    Quote quote;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="projectoffer")
    private Set<Project> Projects;
    @ManyToOne
    User user;
}
