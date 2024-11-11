package org.NAK.surveyit.dto.surveyEdition;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.NAK.surveyit.annotation.EXIST.Exist;
import org.NAK.surveyit.entity.Survey;
import org.NAK.surveyit.repository.SurveyRepository;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyEditionCreateDTO {
    @NotNull
    private LocalDate creationDate;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private Integer year;
    @NotNull
    @Exist(entity = Survey.class , repository = SurveyRepository.class)
    private Long surveyId;
}
