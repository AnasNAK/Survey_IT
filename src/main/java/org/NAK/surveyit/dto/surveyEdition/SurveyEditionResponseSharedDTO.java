package org.NAK.surveyit.dto.surveyEdition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyEditionResponseSharedDTO {
    private Long id;
    private LocalDate creationDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer year;
}
