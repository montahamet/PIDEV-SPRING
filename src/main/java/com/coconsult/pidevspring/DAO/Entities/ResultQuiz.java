package com.coconsult.pidevspring.DAO.Entities;

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
public class ResultQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; // Changed the data type to Long
    String email;
}
