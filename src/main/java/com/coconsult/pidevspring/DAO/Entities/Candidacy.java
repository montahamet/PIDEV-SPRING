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
    String linkedin;
    String github;
    String cv;
    String coverLetter;
    LocalDateTime submissionDate;
    int candidacystatus =0;
    @Column(columnDefinition = "TEXT")
    String linkedinData;
    String skills;
    String country;
    String educationHistory;



    @Builder
    public Candidacy(Long candidacy_id, String candidateName, String link,String linkedin,String github, String cv, String coverLetter, int candidacystatus) {
        this.candidacy_id = candidacy_id;
        this.candidateName = candidateName;
        this.link = link;
        this.linkedin = linkedin;
        this.github = github;
        this.cv = cv;
        this.coverLetter = coverLetter;
        this.submissionDate = LocalDateTime.now(); // Set submissionDate to current system date and time
        this.candidacystatus = candidacystatus;
//        this.job_offer = job_offer;
//        this.user = user;
//        this.Interviews = Interviews;
    }

    ///Relations
//    @JsonBackReference
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "job_offer_id")
    private JobOffer jobOffer;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    User user;

    @JsonIgnore
    @OneToOne(mappedBy = "candidacy", cascade = CascadeType.ALL)
    Interview interview;



    ///Enum
//    public enum StatusCandidacy {
//        PENDING,
//        UNDER_REVIEW, 0
//        REJECTED, -1
//        SELECTED_FOR_INTERVIEW, 1
//        HIRED, 2
//    }
}
