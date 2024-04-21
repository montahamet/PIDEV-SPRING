package com.coconsult.pidevspring.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizQuestion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String question;

    @ElementCollection
    @CollectionTable(name = "options", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "answer")
    private List<String> options;

    @ElementCollection
    @CollectionTable(name = "options", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "is_correct")
    private List<Boolean> isCorrect;
}