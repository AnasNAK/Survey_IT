package org.NAK.surveyit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.NAK.surveyit.enums.QuestionType;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question")

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "questionType")
    private QuestionType questionType;

    @NotBlank
    @Column(name = "text")
    private String text;

    @ColumnDefault("0")
    private Integer answerCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @OneToMany(mappedBy = "question" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private List<Answer> answerList;


}
