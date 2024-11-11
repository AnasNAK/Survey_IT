package org.NAK.surveyit.service.implementations;

import org.NAK.surveyit.dto.survey.SurveyResponseDTO;
import org.NAK.surveyit.dto.surveyEdition.SurveyEditionCreateDTO;
import org.NAK.surveyit.dto.surveyEdition.SurveyEditionResponseDTO;
import org.NAK.surveyit.dto.surveyEdition.SurveyEditionUpdateDTO;
import org.NAK.surveyit.entity.Survey;
import org.NAK.surveyit.entity.SurveyEdition;
import org.NAK.surveyit.exception.EntityNotFoundException;
import org.NAK.surveyit.mapper.SurveyEditionMapper;
import org.NAK.surveyit.repository.SurveyEditionRepository;
import org.NAK.surveyit.repository.SurveyRepository;
import org.NAK.surveyit.service.contracts.SurveyEditionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SurveyEditionServiceImpl implements SurveyEditionService {

    private final SurveyEditionRepository surveyEditionRepository;
    private final SurveyEditionMapper surveyEditionMapper;
    private final SurveyRepository surveyRepository;

    public SurveyEditionServiceImpl(SurveyEditionRepository surveyEditionRepository, SurveyEditionMapper surveyEditionMapper, SurveyRepository surveyRepository) {
        this.surveyEditionRepository = surveyEditionRepository;
        this.surveyEditionMapper = surveyEditionMapper;
        this.surveyRepository = surveyRepository;
    }


    @Override
    public SurveyEditionResponseDTO createSurveyEdition(SurveyEditionCreateDTO surveyEditionCreateDTO) {

        SurveyEdition surveyEdition =  surveyEditionMapper.toSurveyEdition(surveyEditionCreateDTO);
        SurveyEdition savedSurveyEdition = null;
        if (surveyEditionCreateDTO.getSurveyId() != null) {

            Survey survey = surveyRepository.findById(surveyEditionCreateDTO.getSurveyId()).orElseThrow(()-> new EntityNotFoundException("Survey",surveyEditionCreateDTO.getSurveyId()));
            surveyEdition.setSurvey(survey);
            savedSurveyEdition = surveyEditionRepository.save(surveyEdition);

        }
        return surveyEditionMapper.toSurveyEditionResponseDTO(savedSurveyEdition);
    }

    @Override
    public SurveyEditionResponseDTO getSurveyEdition(Long id) {

        return surveyEditionRepository.findById(id)
                .map(surveyEditionMapper::toSurveyEditionResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("SurveyEdition",id));
    }

    @Override
    public SurveyEditionResponseDTO updateSurveyEdition(Long id, SurveyEditionUpdateDTO surveyEditionUpdateDTO) {
        SurveyEdition existingSurveyEdition = surveyEditionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Survey Edition" , id));


        if (surveyEditionUpdateDTO.getSurveyId() == null &&
                surveyEditionUpdateDTO.getCreationDate() == null &&
                surveyEditionUpdateDTO.getYear() == null &&
                surveyEditionUpdateDTO.getEndDate() == null &&
                surveyEditionUpdateDTO.getStartDate() == null) {
            throw new RuntimeException("No data provided to be updated; no need to update");
        }


        Optional.ofNullable(surveyEditionUpdateDTO.getCreationDate())
                .filter(localDate -> !localDate.equals(existingSurveyEdition.getCreationDate()))
                .ifPresent(existingSurveyEdition::setCreationDate);

        Optional.ofNullable(surveyEditionUpdateDTO.getYear())
                .filter(year -> !year.equals(existingSurveyEdition.getYear()))
                .ifPresent(existingSurveyEdition::setYear);

        Optional.ofNullable(surveyEditionUpdateDTO.getStartDate())
                .filter(localDate -> !localDate.equals(existingSurveyEdition.getStartDate()))
                .ifPresent(existingSurveyEdition::setStartDate);

        Optional.ofNullable(surveyEditionUpdateDTO.getEndDate())
                .filter(localDate -> !localDate.equals(existingSurveyEdition.getEndDate()))
                .ifPresent(existingSurveyEdition::setEndDate);

        if (surveyEditionUpdateDTO.getSurveyId() != null &&
                !surveyEditionUpdateDTO.getSurveyId().equals(existingSurveyEdition.getSurvey().getId())) {

            if (surveyRepository.existsById(surveyEditionUpdateDTO.getSurveyId())) {
                Survey survey = surveyRepository.getReferenceById(surveyEditionUpdateDTO.getSurveyId());
                existingSurveyEdition.setSurvey(survey);
            } else {
                throw new EntityNotFoundException("Survey with the id " , surveyEditionUpdateDTO.getSurveyId());
            }
        }

        SurveyEdition updatedSurveyEdition = surveyEditionRepository.save(existingSurveyEdition);

        return surveyEditionMapper.toSurveyEditionResponseDTO(updatedSurveyEdition);
    }



    @Override
    public void deleteSurveyEdition(Long id) {
        if (!surveyEditionRepository.existsById(id)) {
            throw new EntityNotFoundException("Survey Edition" , id);
        }
        surveyEditionRepository.deleteById(id);

    }


    @Override
    public List<SurveyEditionResponseDTO> getSurveyEditions() {
        List<SurveyEdition> surveyEditions = surveyEditionRepository.findAll();

        return surveyEditions.stream()
                .map(surveyEditionMapper::toSurveyEditionResponseDTO)
                .collect(Collectors.toList());
    }
}
