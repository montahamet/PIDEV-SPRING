package com.coconsult.pidevspring.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "QuizQuestion")
public class QuizQuestion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionId")
    private Long questionId;
    @Column(name = "question")
    private String question;

    @ElementCollection
    @CollectionTable(name = "options", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "options")
    private List<Option> options;
}
