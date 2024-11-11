package org.NAK.surveyit.mapper;

import org.NAK.surveyit.dto.surveyEdition.SurveyEditionCreateDTO;
import org.NAK.surveyit.dto.surveyEdition.SurveyEditionResponseDTO;
import org.NAK.surveyit.dto.surveyEdition.SurveyEditionUpdateDTO;
import org.NAK.surveyit.entity.SurveyEdition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SurveyEditionMapper {
SurveyEdition toSurveyEdition(SurveyEditionCreateDTO surveyEditionCreateDTO);
SurveyEdition toSurveyEdition(SurveyEditionUpdateDTO surveyEditionUpdateDTO);

@Mapping(source = "survey" ,target = "survey")
@Mapping(source = "id" ,target ="id" )
SurveyEditionResponseDTO toSurveyEditionResponseDTO(SurveyEdition surveyEdition);
}
