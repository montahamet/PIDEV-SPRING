package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)


public class mark {
    @Id
    String candidateName; // Assuming 'Name' is the primary key
    int html;
    int python;
    int java;
    int c;
    int javascript;
    int kotlin;
    int swift;
    int react;
    int angular;
    int spring;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidacy_id")
    private Candidacy candidacy;
}
