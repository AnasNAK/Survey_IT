package org.NAK.surveyit.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.NAK.surveyit.dto.question.QuestionSharedResponseDTO;
import org.NAK.surveyit.dto.surveyEdition.SurveyEditionResponseSharedDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponseDTO {

    private Long id;
    private String title;
    private SurveyEditionResponseSharedDTO surveyEdition;
    private SubjectParentResponseDTO parent;
    private List<QuestionSharedResponseDTO> questionList;


}
