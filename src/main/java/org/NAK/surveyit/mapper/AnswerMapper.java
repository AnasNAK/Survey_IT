package org.NAK.surveyit.mapper;

import org.NAK.surveyit.dto.answer.AnswerCreateDTO;
import org.NAK.surveyit.dto.answer.AnswerResponseDTO;
import org.NAK.surveyit.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    @Mapping(source = "text" , target = "text")
    Answer toAnswer(AnswerCreateDTO answerCreateDTO);

    @Mapping(source = "question" , target = "question")
    AnswerResponseDTO toResponse(Answer answer);
}