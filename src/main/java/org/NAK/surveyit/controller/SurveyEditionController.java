package org.NAK.surveyit.controller;

import org.NAK.surveyit.annotation.EXIST.Exist;
import org.NAK.surveyit.dto.surveyEdition.SurveyEditionCreateDTO;
import org.NAK.surveyit.dto.surveyEdition.SurveyEditionResponseDTO;
import org.NAK.surveyit.dto.surveyEdition.SurveyEditionUpdateDTO;
import org.NAK.surveyit.entity.SurveyEdition;
import org.NAK.surveyit.repository.SurveyEditionRepository;
import org.NAK.surveyit.service.contracts.SurveyEditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveyEditions")
public class SurveyEditionController {

    private final SurveyEditionService surveyEditionService;

    @Autowired
    public SurveyEditionController(SurveyEditionService surveyEditionService) {
        this.surveyEditionService = surveyEditionService;
    }

    @PostMapping
    public ResponseEntity<SurveyEditionResponseDTO> createSurveyEdition(@RequestBody SurveyEditionCreateDTO surveyEditionCreateDTO) {
        SurveyEditionResponseDTO createdSurveyEdition = surveyEditionService.createSurveyEdition(surveyEditionCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSurveyEdition);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyEditionResponseDTO> getSurveyEdition(@PathVariable Long id) {
        SurveyEditionResponseDTO surveyEdition = surveyEditionService.getSurveyEdition(id);
        return ResponseEntity.ok(surveyEdition);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SurveyEditionResponseDTO> updateSurveyEdition(
            @PathVariable @Exist(entity = SurveyEdition.class, repository = SurveyEditionRepository.class) Long id,
            @RequestBody SurveyEditionUpdateDTO surveyEditionUpdateDTO) {
        SurveyEditionResponseDTO updatedSurveyEdition = surveyEditionService.updateSurveyEdition(id, surveyEditionUpdateDTO);
        return ResponseEntity.ok(updatedSurveyEdition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurveyEdition(
            @PathVariable @Exist(entity = SurveyEdition.class, repository = SurveyEditionRepository.class) Long id) {
        surveyEditionService.deleteSurveyEdition(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SurveyEditionResponseDTO>> getSurveyEditions() {
        List<SurveyEditionResponseDTO> surveyEditions = surveyEditionService.getSurveyEditions();
        return ResponseEntity.ok(surveyEditions);
    }
}
