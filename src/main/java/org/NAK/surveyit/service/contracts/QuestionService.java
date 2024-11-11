package org.NAK.surveyit.service.contracts;

import org.NAK.surveyit.dto.question.QuestionCreateDTO;
import org.NAK.surveyit.dto.question.QuestionResponseDTO;

import java.util.List;

public interface QuestionService {

    QuestionResponseDTO createQuestion(QuestionCreateDTO questionCreateDTO);
    List<QuestionResponseDTO> findAllQuestions();
    QuestionResponseDTO findQuestionById(Long id);
    boolean deleteQuestion(Long id);
    QuestionResponseDTO updateQuestion(Long id , QuestionCreateDTO questionCreateDTO);
}
