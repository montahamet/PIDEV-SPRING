package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Candidacy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long candidacy_id ;
    String candidateName;
    String email;
    String link;
    String cv;
    String coverLetter;
    LocalDateTime submissionDate;
    @Enumerated(EnumType.STRING)
    StatusCandidacy candidacystatus;


    @Builder
    public Candidacy(Long candidacy_id, String candidateName, String link, String cv, String coverLetter, StatusCandidacy candidacystatus) {
        this.candidacy_id = candidacy_id;
        this.candidateName = candidateName;
        this.link = link;
        this.cv = cv;
        this.coverLetter = coverLetter;
        this.submissionDate = LocalDateTime.now(); // Set submissionDate to current system date and time
        this.candidacystatus = candidacystatus;
//        this.job_offer = job_offer;
//        this.user = user;
//        this.Interviews = Interviews;
    }

    ///Relations
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "job_offer_id")
    private JobOffer jobOffer;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    User user;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="candidacy")
    private Set<Interview> Interviews;


    ///Enum
    public enum StatusCandidacy {
        PENDING,
        UNDER_REVIEW,
        REJECTERD,
        SELECTED_FOR_INTERVIW
    }
}
