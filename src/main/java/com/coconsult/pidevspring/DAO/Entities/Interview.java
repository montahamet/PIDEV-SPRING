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
public class Interview implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long interview_id ;
    LocalDateTime dateInterview;
    @Enumerated(EnumType.STRING)
    TypeInterview type;
    @ManyToOne
    User userHR;
    @ManyToOne
    User userCANDIDATE;
}
