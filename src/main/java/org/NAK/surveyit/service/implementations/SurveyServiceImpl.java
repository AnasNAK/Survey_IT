package org.NAK.surveyit.service.implementations;

import org.NAK.surveyit.dto.survey.SurveyCreateDTO;
import org.NAK.surveyit.dto.survey.SurveyResponseDTO;
import org.NAK.surveyit.dto.survey.SurveyUpdateDTO;
import org.NAK.surveyit.entity.Owner;
import org.NAK.surveyit.entity.Survey;
import org.NAK.surveyit.exception.EntityNotFoundException;
import org.NAK.surveyit.mapper.SurveyMapper;
import org.NAK.surveyit.repository.OwnerRepository;
import org.NAK.surveyit.repository.SurveyRepository;
import org.NAK.surveyit.service.contracts.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyServiceImpl implements SurveyService {


    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;
    private final OwnerRepository ownerRepository;


    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository, SurveyMapper surveyMapper , OwnerRepository ownerRepository) {
        this.surveyRepository = surveyRepository;
        this.surveyMapper = surveyMapper;
        this.ownerRepository = ownerRepository;
    }


    @Override
    public SurveyResponseDTO createSurvey(SurveyCreateDTO surveyCreateDTO) {

        Survey survey = surveyMapper.toEntity(surveyCreateDTO);
        Survey SurveySaved = null;
        if (surveyCreateDTO.getOwnerId() != null) {
            Owner owner =ownerRepository.findById(surveyCreateDTO.getOwnerId()).orElseThrow(()-> new EntityNotFoundException ("Owner",surveyCreateDTO.getOwnerId()));
            survey.setOwner(owner);

            SurveySaved = surveyRepository.save(survey);
        }
        return surveyMapper.toDto(SurveySaved);
    }

    @Override
    public SurveyResponseDTO updateSurvey(Long id, SurveyUpdateDTO surveyUpdateDTO) {
        Survey existingSurvey = surveyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Survey", id));

        if (surveyUpdateDTO.getTitle() != null) {
            existingSurvey.setTitle(surveyUpdateDTO.getTitle());
        }

        if (surveyUpdateDTO.getDescription() != null) {
            existingSurvey.setDescription(surveyUpdateDTO.getDescription());
        }

        if (surveyUpdateDTO.getOwnerId() != null) {
            Owner newOwner = ownerRepository.findById(surveyUpdateDTO.getOwnerId())
                    .orElseThrow(() -> new EntityNotFoundException("Owner  " , surveyUpdateDTO.getOwnerId() ));
            existingSurvey.setOwner(newOwner);
        }

        Survey updatedSurvey = surveyRepository.save(existingSurvey);

        return surveyMapper.toDto(updatedSurvey);
    }


    @Override
    public void deleteSurvey(Long id) {
        if (!surveyRepository.existsById(id)) {
            throw new EntityNotFoundException("Survey", id);
        }
        surveyRepository.deleteById(id);

    }

    @Override
    public SurveyResponseDTO getSurveyById(Long id) {

        return surveyRepository.findById(id)
                .map(surveyMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Survey", id));
    }

    @Override
    public List<SurveyResponseDTO> getAllSurveys() {
       return surveyRepository.findAll()
               .stream()
               .map(surveyMapper::toDto)
               .collect(Collectors.toList());
    }

    @Override
    public SurveyResponseDTO getSurveyByTitle(String title) {
        return surveyRepository.findByTitle(title)
                .map(surveyMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Survey", title));
    }
}
