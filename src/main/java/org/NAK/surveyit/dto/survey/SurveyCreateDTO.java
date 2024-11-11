package org.NAK.surveyit.dto.survey;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.NAK.surveyit.annotation.EXIST.Exist;
import org.NAK.surveyit.annotation.UNIQUE.Unique;
import org.NAK.surveyit.entity.Owner;
import org.NAK.surveyit.entity.Survey;
import org.NAK.surveyit.repository.OwnerRepository;
import org.NAK.surveyit.repository.SurveyRepository;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyCreateDTO
{

    @NotBlank(message = "Title cannot be empty")
    @Unique(message = "Survey title must be unique",entity = Survey.class, repository = SurveyRepository.class ,  column = "Title")
    private String title;

    @NotBlank
    private String description;

    @Positive
    @NotNull
    @Exist(entity = Owner.class, repository = OwnerRepository.class)
    private Long ownerId;
}
