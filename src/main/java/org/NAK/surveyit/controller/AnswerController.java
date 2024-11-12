package org.NAK.surveyit.controller;

import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import org.NAK.surveyit.annotation.EXIST.Exist;
import org.NAK.surveyit.dto.answer.AnswerCreateDTO;
import org.NAK.surveyit.dto.answer.AnswerResponseDTO;
import org.NAK.surveyit.entity.Answer;
import org.NAK.surveyit.repository.AnswerRepository;
import org.NAK.surveyit.service.contracts.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping
    public AnswerResponseDTO createAnswer(@Valid @RequestBody AnswerCreateDTO answerCreateDTO) {
        return answerService.createAnswer(answerCreateDTO);
    }

    @GetMapping
    public List<AnswerResponseDTO> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GetMapping("/{answerId}")
    public AnswerResponseDTO getAnswer(
            @PathVariable("answerId")
            @Exist(entity = Answer.class, repository = AnswerRepository.class) Long id) {
        return answerService.getAnswerById(id);
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<String> deleteAnswer(
            @PathVariable("answerId")
            @Exist(entity = Answer.class, repository = AnswerRepository.class) Long id, ServletRequest servletRequest) {
        if (answerService.deleteById(id)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Answer Deleted succefully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something happend !!");
    }
}