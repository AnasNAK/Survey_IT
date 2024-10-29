package org.NAK.surveyit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    @OneToMany(mappedBy = "parentSubject" ,cascade = CascadeType.ALL)
    private Set<Subject> subSubjects = new HashSet<>();

    @ManyToOne
    private Subject parentSubject;

    @ManyToOne
    private SurveyEdition surveyEdition;

    @OneToMany(mappedBy = "subject" ,cascade = CascadeType.ALL)
    private List<Question> questions ;


}
