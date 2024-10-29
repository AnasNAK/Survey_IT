package org.NAK.surveyit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "surveyedition")

public class SurveyEdition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate creationDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer year;

    @ManyToOne
    private Survey survey;

    @OneToMany(mappedBy = "surveyEdition" , cascade = CascadeType.ALL)
    private List<Subject> subjects;
}
