package org.NAK.surveyit.service.contracts;

import org.NAK.surveyit.dto.survey.SurveyCreateDTO;
import org.NAK.surveyit.dto.survey.SurveyResponseDTO;
import org.NAK.surveyit.dto.survey.SurveyUpdateDTO;

import java.util.List;

public interface SurveyService {
    SurveyResponseDTO createSurvey(SurveyCreateDTO surveyCreateDTO);
    SurveyResponseDTO updateSurvey(Long id ,SurveyUpdateDTO surveyUpdateDTO );
    void deleteSurvey(Long id );
    SurveyResponseDTO getSurveyById(Long id );
    List<SurveyResponseDTO> getAllSurveys();
    SurveyResponseDTO getSurveyByTitle(String title);

}
