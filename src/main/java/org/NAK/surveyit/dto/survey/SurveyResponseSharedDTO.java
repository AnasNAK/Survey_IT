package org.NAK.surveyit.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyResponseSharedDTO {
    private Long id;
    private String title;
    private String description;
}
