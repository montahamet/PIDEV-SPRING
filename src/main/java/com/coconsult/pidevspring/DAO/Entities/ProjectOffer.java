package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
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
    private Long offer_id ;
    private String ProjectTitle;
    private String description;
    private LocalDate postedDate;

    @Enumerated(EnumType.STRING)
    private ProjectOfferStatus status_offer;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="projectofferquote")
    private Set<Quote> Quotes=new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="projectoffer")
    private Set<Project> Projects=new HashSet<>();

    @JsonIgnore
    @ManyToOne
    private User user;
}
