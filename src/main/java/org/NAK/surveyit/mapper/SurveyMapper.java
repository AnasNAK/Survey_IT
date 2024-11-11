package org.NAK.surveyit.mapper;

import org.NAK.surveyit.dto.survey.SurveyCreateDTO;
import org.NAK.surveyit.dto.survey.SurveyResponseDTO;
import org.NAK.surveyit.dto.survey.SurveyUpdateDTO;
import org.NAK.surveyit.entity.Survey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SurveyMapper {
    Survey toEntity(SurveyCreateDTO dto);

    Survey toEntity(SurveyUpdateDTO dto);
    @Mapping(source = "owner" , target = "owner")
    SurveyResponseDTO toDto(Survey entity);
}
