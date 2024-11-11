package org.NAK.surveyit.controller;

import jakarta.validation.Valid;
import org.NAK.surveyit.annotation.EXIST.Exist;
import org.NAK.surveyit.dto.subject.MainSubjectResponse;
import org.NAK.surveyit.dto.subject.SubjectCreateDTO;
import org.NAK.surveyit.dto.subject.SubjectResponseDTO;
import org.NAK.surveyit.entity.Subject;
import org.NAK.surveyit.repository.SubjectRepository;
import org.NAK.surveyit.service.contracts.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public SubjectResponseDTO createSubject(@Valid @RequestBody SubjectCreateDTO subjectCreateDTO){
        return subjectService.createSubject(subjectCreateDTO);
    }

    @GetMapping
    public List<SubjectResponseDTO> getAllSubjects(){
        return subjectService.findAllSubjects();
    }

    @GetMapping("/mainsubject")
    public List<MainSubjectResponse> getAllMainSubjects() {
        return subjectService.findAllMainSubjects();
    }

    @GetMapping("/{subjectId}")
    public Object getSubjectById(@PathVariable("subjectId") @Exist(entity = Subject.class, repository = SubjectRepository.class) Long id){
        return subjectService.findSubjectById(id);
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<?> deleteSubjectById(@PathVariable("subjectId")
                                               @Exist(entity = Subject.class, repository = SubjectRepository.class)    Long id){
        if(subjectService.deleteById(id)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Subject Deleted Succefully");
        }

        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{subjectId}")
    public Object updateSubject(
            @PathVariable("subjectId")
            @Exist(entity = Subject.class, repository = SubjectRepository.class) Long id,
            @RequestBody SubjectCreateDTO subjectCreateDTO){
        return subjectService.updateSubject(id , subjectCreateDTO);
    }

}
