package org.NAK.surveyit.dto.answer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.NAK.surveyit.dto.question.QuestionSharedResponseDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponseDTO {

    private Long id;
    private String text;
    private Integer selectionCount;
    private QuestionSharedResponseDTO question;
}
