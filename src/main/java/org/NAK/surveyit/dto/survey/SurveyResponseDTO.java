package org.NAK.surveyit.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.NAK.surveyit.dto.owner.OwnerResponseSharedSurvey;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyResponseDTO {
    private Long id;
    private String title;
    private String description;
    private OwnerResponseSharedSurvey owner;
}
