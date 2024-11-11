package org.NAK.surveyit.controller;

import jakarta.validation.Valid;
import org.NAK.surveyit.annotation.EXIST.Exist;
import org.NAK.surveyit.dto.survey.SurveyCreateDTO;
import org.NAK.surveyit.dto.survey.SurveyResponseDTO;
import org.NAK.surveyit.dto.survey.SurveyUpdateDTO;
import org.NAK.surveyit.entity.Survey;
import org.NAK.surveyit.repository.SurveyRepository;
import org.NAK.surveyit.service.contracts.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping
    public ResponseEntity<SurveyResponseDTO> createSurvey(@Valid  @RequestBody SurveyCreateDTO surveyCreateDTO) {
        SurveyResponseDTO createdSurvey = surveyService.createSurvey(surveyCreateDTO);
        return new ResponseEntity<>(createdSurvey , HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyResponseDTO> getSurveyById(
            @PathVariable("id")
            @Exist(message = "Survey with ID ${validatedValue} does not exist" ,entity = Survey.class ,repository = SurveyRepository.class) Long id) {

        SurveyResponseDTO survey = surveyService.getSurveyById(id);
        return ResponseEntity.ok(survey);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SurveyResponseDTO> updateSurvey(
            @PathVariable("id")
            @Exist(message = "Survey with ID ${validatedValue} does not exist" ,entity = Survey.class ,repository = SurveyRepository.class) Long id
            , @Valid @RequestBody SurveyUpdateDTO surveyUpdateDTO){
        SurveyResponseDTO survey= surveyService.updateSurvey(id,surveyUpdateDTO);
        return ResponseEntity.ok(survey);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(
            @PathVariable("id")
            @Exist(message = "Survey with ID ${validatedValue} does not exist" ,entity = Survey.class ,repository = SurveyRepository.class) Long id) {

        surveyService.deleteSurvey(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SurveyResponseDTO>> getAllSurveys() {
        List<SurveyResponseDTO> surveys = surveyService.getAllSurveys();
        return ResponseEntity.ok(surveys);
    }

    @GetMapping("/name/{title}")
    public ResponseEntity<SurveyResponseDTO> getSurveyByTitle(
            @PathVariable("title") String title) {
        SurveyResponseDTO survey = surveyService.getSurveyByTitle(title);
        return ResponseEntity.ok(survey);
    }

}
