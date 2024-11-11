package org.NAK.surveyit.mapper;

import org.NAK.surveyit.dto.question.QuestionCreateDTO;
import org.NAK.surveyit.dto.question.QuestionResponseDTO;
import org.NAK.surveyit.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(source = "text" , target = "text")
    Question toQuestion(QuestionCreateDTO questionCreateDTO);

    @Mapping(source = "answerList" , target = "answerList")
    QuestionResponseDTO toResponse(Question question);

}