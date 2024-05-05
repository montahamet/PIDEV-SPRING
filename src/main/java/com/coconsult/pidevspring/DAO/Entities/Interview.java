package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Interview implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long interview_id ;
    LocalDateTime dateInterview;
    @Enumerated(EnumType.STRING)
    StatusInterview statusInterview;
    Boolean passed;
    String candidateName;
    String email;
    // Setter method for candidateName
    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    // Getter method for email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter method for candidateName (if not generated by Lombok)
    public String getCandidateName() {
        return candidateName;
    }
    public boolean isPassed() {
        return passed;
    }


    ///Relations
    @JsonIgnore
    @ManyToOne
    User user;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "candidacy_id")
    Candidacy candidacy;


    ///Enum
    public enum StatusInterview {
        SCHEDULED,
        IN_PROGRESS,
        COMPLETED,
        CANCELED
    }

}
