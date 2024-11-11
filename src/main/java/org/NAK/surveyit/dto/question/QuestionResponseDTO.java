package org.NAK.surveyit.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.NAK.surveyit.dto.answer.AnswerSharedResponseDTO;
import org.NAK.surveyit.dto.subject.SubjectSharedResponseDTO;
import org.NAK.surveyit.enums.QuestionType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDTO {

    private Long id;
    private QuestionType questionType;
    private String text;
    private Integer answerCount;
    private SubjectSharedResponseDTO subject;
    private List<AnswerSharedResponseDTO> answerList;

}