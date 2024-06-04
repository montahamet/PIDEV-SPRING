package com.coconsult.pidevspring.DAO.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.coconsult.pidevspring.DAO.Entities.ProjectOfferStatus;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProjectOffer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long offerId ;
    String projectTitle;
    String description;
    LocalDate postedDate;
    String companyname; // New attribute
    String companyemail; // New attribute

    @Enumerated(EnumType.STRING)
    ProjectOfferStatus status;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="projectofferquote", fetch = FetchType.LAZY)
    Set<Quote> Quotes=new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="projectoffer")
    Set<Project> Projects=new HashSet<>();

    @JsonIgnore
    @ManyToOne
    User user;
}
