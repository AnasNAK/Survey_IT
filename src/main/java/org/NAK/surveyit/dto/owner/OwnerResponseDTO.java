package org.NAK.surveyit.dto.owner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.NAK.surveyit.dto.survey.SurveyResponseForOwner;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerResponseDTO {

    private Long id;
    private String name;
    private List<SurveyResponseForOwner> surveys;

}
