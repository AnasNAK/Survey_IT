package org.NAK.surveyit.controller;


import jakarta.validation.Valid;
import org.NAK.surveyit.annotation.EXIST.Exist;
import org.NAK.surveyit.dto.question.QuestionCreateDTO;
import org.NAK.surveyit.dto.question.QuestionResponseDTO;
import org.NAK.surveyit.entity.Question;
import org.NAK.surveyit.repository.QuestionRepository;
import org.NAK.surveyit.service.contracts.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public QuestionResponseDTO saveQuestion(@RequestBody @Valid QuestionCreateDTO questionCreateDTO){
        return questionService.createQuestion(questionCreateDTO);
    }

    @GetMapping
    public List<QuestionResponseDTO> getAllQuestions() {
        return questionService.findAllQuestions();
    }

    @GetMapping("/{questionId}")
    public QuestionResponseDTO findOneQuestion(
            @PathVariable("questionId")
            @Exist(entity = Question.class, repository = QuestionRepository.class) Long id){
        return questionService.findQuestionById(id);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteQuestion(
            @PathVariable("questionId")
            @Exist(entity = Question.class, repository = QuestionRepository.class) Long id) {
        if(questionService.deleteQuestion(id)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Question deleted succefulyy");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete this question , something happend");
    }


    @PatchMapping("/{questionId}")
    public QuestionResponseDTO updateQuestion(
            @PathVariable("questionId")
            @Exist(entity = Question.class, repository = QuestionRepository.class) Long id ,
            @RequestBody QuestionCreateDTO questionCreateDTO){
        return questionService.updateQuestion(id , questionCreateDTO);
    }
}
