package org.NAK.surveyit.dto.surveyEdition;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.NAK.surveyit.annotation.EXIST.Exist;
import org.NAK.surveyit.dto.survey.SurveyResponseSharedDTO;
import org.NAK.surveyit.entity.Survey;
import org.NAK.surveyit.repository.SurveyRepository;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyEditionResponseDTO {

    private Long id;
    private LocalDate creationDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer year;
    private SurveyResponseSharedDTO survey;

}
