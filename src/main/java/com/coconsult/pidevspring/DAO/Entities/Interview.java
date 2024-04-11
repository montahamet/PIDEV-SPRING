package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Data
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


    ///Relations
    @JsonIgnore
    @ManyToOne
    User user;

    @JsonManagedReference
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
