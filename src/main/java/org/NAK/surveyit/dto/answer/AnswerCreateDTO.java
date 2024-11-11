package org.NAK.surveyit.dto.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.NAK.surveyit.annotation.EXIST.Exist;
import org.NAK.surveyit.annotation.UNIQUE.Unique;
import org.NAK.surveyit.entity.Answer;
import org.NAK.surveyit.entity.Question;
import org.NAK.surveyit.repository.AnswerRepository;
import org.NAK.surveyit.repository.QuestionRepository;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerCreateDTO {

    @NotBlank
    private String text;

    @NotNull
    private Integer selectionCount = 0;

    @Exist(entity = Question.class, repository = QuestionRepository.class)
    private Long questionId;
}

