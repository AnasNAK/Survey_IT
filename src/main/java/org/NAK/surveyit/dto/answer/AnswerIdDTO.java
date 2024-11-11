package org.NAK.surveyit.dto.answer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.NAK.surveyit.annotation.EXIST.Exist;
import org.NAK.surveyit.entity.Answer;
import org.NAK.surveyit.repository.AnswerRepository;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerIdDTO {

    @Exist(entity = Answer.class, repository = AnswerRepository.class)
    private Long answerId;

}