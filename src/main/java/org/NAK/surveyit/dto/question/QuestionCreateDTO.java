package org.NAK.surveyit.dto.question;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.NAK.surveyit.annotation.EXIST.Exist;
import org.NAK.surveyit.annotation.UNIQUE.Unique;
import org.NAK.surveyit.entity.Question;
import org.NAK.surveyit.entity.Subject;
import org.NAK.surveyit.enums.QuestionType;
import org.NAK.surveyit.repository.QuestionRepository;
import org.NAK.surveyit.repository.SubjectRepository;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateDTO {

    @NotBlank
    @Unique(entity = Question.class, repository = QuestionRepository.class, column = "Text")
    private String text;

    private QuestionType questionType;

    @Exist(entity = Subject.class, repository = SubjectRepository.class)
    private Long subjectId;

}
