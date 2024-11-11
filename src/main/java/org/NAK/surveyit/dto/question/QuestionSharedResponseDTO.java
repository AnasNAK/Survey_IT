package org.NAK.surveyit.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.NAK.surveyit.enums.QuestionType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionSharedResponseDTO {

    private Long id;
    private QuestionType questionType;
    private String text;
    private Integer answerCount;

}