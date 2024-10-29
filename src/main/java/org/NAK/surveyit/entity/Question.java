package org.NAK.surveyit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.NAK.surveyit.enums.QuestionType;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question")

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private int answerCount;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @ManyToOne
    private Subject subject;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

}
