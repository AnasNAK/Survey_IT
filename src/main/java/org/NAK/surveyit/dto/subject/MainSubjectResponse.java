package org.NAK.surveyit.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.NAK.surveyit.dto.surveyEdition.SurveyEditionResponseSharedDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainSubjectResponse {

    private Long id;
    private String title;
    private SurveyEditionResponseSharedDTO surveyEdition;
    private List<SubjectSharedResponseDTO> subSubjects;

}
