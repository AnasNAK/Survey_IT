package org.NAK.surveyit.service.contracts;

import org.NAK.surveyit.dto.answer.AnswerCreateDTO;
import org.NAK.surveyit.dto.answer.AnswerResponseDTO;

import java.util.List;

public interface AnswerService {
    AnswerResponseDTO createAnswer(AnswerCreateDTO answerCreateDTO);
    List<AnswerResponseDTO> getAllAnswers();
    AnswerResponseDTO getAnswerById(Long id);
    boolean deleteById(Long id);
}

