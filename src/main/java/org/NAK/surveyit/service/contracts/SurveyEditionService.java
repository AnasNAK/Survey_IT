package org.NAK.surveyit.service.contracts;

import org.NAK.surveyit.dto.surveyEdition.SurveyEditionCreateDTO;
import org.NAK.surveyit.dto.surveyEdition.SurveyEditionResponseDTO;
import org.NAK.surveyit.dto.surveyEdition.SurveyEditionUpdateDTO;

import java.util.List;

public interface SurveyEditionService {
    SurveyEditionResponseDTO createSurveyEdition(SurveyEditionCreateDTO surveyEditionCreateDTO);
    SurveyEditionResponseDTO getSurveyEdition(Long id);
    SurveyEditionResponseDTO updateSurveyEdition(Long id, SurveyEditionUpdateDTO surveyEditionUpdateDTO);
    void deleteSurveyEdition(Long id);
    List<SurveyEditionResponseDTO> getSurveyEditions();

}
