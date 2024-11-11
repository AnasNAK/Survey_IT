package org.NAK.surveyit.service.implementations;

import jakarta.persistence.EntityNotFoundException;
import org.NAK.surveyit.dto.subject.MainSubjectResponse;
import org.NAK.surveyit.dto.subject.SubjectCreateDTO;
import org.NAK.surveyit.dto.subject.SubjectResponseDTO;
import org.NAK.surveyit.dto.subject.SubjectSharedResponseDTO;
import org.NAK.surveyit.entity.Subject;
import org.NAK.surveyit.entity.SurveyEdition;
import org.NAK.surveyit.mapper.SubjectMapper;
import org.NAK.surveyit.repository.SubjectRepository;
import org.NAK.surveyit.repository.SurveyEditionRepository;
import org.NAK.surveyit.service.contracts.SubjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final SurveyEditionRepository surveyEditionRepository;
    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectMapper subjectMapper, SurveyEditionRepository surveyEditionRepository) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
        this.surveyEditionRepository = surveyEditionRepository;
    }


    @Override
    public SubjectResponseDTO createSubject(SubjectCreateDTO subjectCreateDTO) {
        Subject parent = null;
        SurveyEdition surveyEdition = null;
        Subject createdSubject = subjectMapper.toSubject(subjectCreateDTO);

        if (subjectCreateDTO.getSurveyEditionId() != null) {
            Long SurveyEditionId = subjectCreateDTO.getSurveyEditionId();
            if (surveyEditionRepository.existsById(SurveyEditionId)) {
                surveyEdition = surveyEditionRepository.findById(SurveyEditionId).get();
                createdSubject.setSurveyEdition(surveyEdition);
            } else {
                throw new RuntimeException("Survey Edition with the id " + SurveyEditionId + " Does not exist");
            }

        } else {
            throw new RuntimeException("Survey Edition id must be provided");
        }


        if (subjectCreateDTO.getParentId() != null) {
            if (subjectRepository.existsById(subjectCreateDTO.getParentId())) {
                parent = subjectRepository.findById(subjectCreateDTO.getParentId()).get();
                createdSubject.setParent(parent);
            } else {
                throw new EntityNotFoundException("Subject Parent with the id " + subjectCreateDTO.getParentId() + " does not exist");
            }
        }

        Subject saveSubject = subjectRepository.save(createdSubject);
        return subjectMapper.toResponseSubject(saveSubject);

    }

    @Override
    public List<SubjectResponseDTO> findAllSubjects() {
        List<Subject> subjectList = subjectRepository.findAll();
        if (subjectList.isEmpty()) {
            throw new RuntimeException("No Subjects found");
        }
        return subjectList.stream().map(subjectMapper::toResponseSubject).toList();
    }

    @Override
    public List<MainSubjectResponse> findAllMainSubjects() {
        List<Subject> subjectList = subjectRepository.findAllByParentIdIsNull();
        if (subjectList.isEmpty()) {
            throw new RuntimeException("No Subjects found");
        }

        return subjectList.stream().map(subject -> {
            List<Subject> subSubjects = subjectRepository.findAllByParentId(subject.getId());
            MainSubjectResponse mainSubjectResponse = subjectMapper.toMainSubjectResponse(subject);
            List<SubjectSharedResponseDTO> subs = subSubjects.stream().map(subjectMapper::toSubSubject).toList();
            mainSubjectResponse.setSubSubjects(subs);
            return mainSubjectResponse;
        }).toList();

    }

    @Override
    public Object findSubjectById(Long id) {
        if (subjectRepository.existsById(id)) {

            Subject subject = subjectRepository.findById(id).get();


            if (subject.getParent() == null) {
                System.out.println("test");
                List<Subject> subSubjects = subjectRepository.findAllByParentId(subject.getId());
                List<SubjectSharedResponseDTO> subs = subSubjects.stream().map(subjectMapper::toSubSubject).toList();
                MainSubjectResponse mainSubjectResponse = subjectMapper.toMainSubjectResponse(subject);
                mainSubjectResponse.setSubSubjects(subs);
                return mainSubjectResponse;
            } else {
                return subjectMapper.toResponseSubject(subject);
            }

        } else {
            throw new EntityNotFoundException("Didnt find the subject with the id " + id);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new EntityNotFoundException("Subject with the id does not exist " + id);
        }

        List<Subject> subSubjects = subjectRepository.findAllByParentId(id);

        if (!subSubjects.isEmpty()) {
            subSubjects.forEach(subjectRepository::delete);
        }
        subjectRepository.deleteById(id);
        return true;
    }

    @Override
    public Object updateSubject(Long id, SubjectCreateDTO subjectCreateDTO) {

        validateUpdateData(subjectCreateDTO);

        if (!subjectRepository.existsById(id)) {
            throw new EntityNotFoundException("Subject was not found");
        }

        Subject existingSubject = subjectRepository.getReferenceById(id);
        Subject updatedSubject = subjectMapper.toSubject(subjectCreateDTO);

        Optional.ofNullable(subjectCreateDTO.getTitle())
                .filter(String -> !String.equals(existingSubject.getTitle()))
                .ifPresent(existingSubject::setTitle);


        if (subjectCreateDTO.getSurveyEditionId() != null && !subjectCreateDTO.getSurveyEditionId().equals(existingSubject.getSurveyEdition().getId())) {
            SurveyEdition surveyEdition = surveyEditionRepository.findById(subjectCreateDTO.getSurveyEditionId())
                    .orElseThrow(() -> new EntityNotFoundException("Survey Edition not found"));
            updatedSubject.setSurveyEdition(surveyEdition);
        } else {
            updatedSubject.setSurveyEdition(existingSubject.getSurveyEdition());
        }


        if (existingSubject.getParent() != null) {
            if (subjectCreateDTO.getParentId() != null && !subjectCreateDTO.getParentId().equals(existingSubject.getParent().getId())) {
                Subject parent = subjectRepository.findById(subjectCreateDTO.getParentId())
                        .orElseThrow(() -> new EntityNotFoundException("Parent not found"));

                if (parent.getParent() != null) {
                    throw new RuntimeException("The provided parent subject is not a main subject");
                }
                updatedSubject.setParent(parent);
            } else {
                updatedSubject.setParent(existingSubject.getParent());
            }

            updatedSubject.setQuestionList(existingSubject.getQuestionList());
        }


        updatedSubject.setId(id);
        Subject savedSubject = subjectRepository.save(updatedSubject);
        return existingSubject.getParent() == null
                ? subjectMapper.toMainSubjectResponse(savedSubject)
                : subjectMapper.toResponseSubject(savedSubject);
    }

    private void validateUpdateData(SubjectCreateDTO subjectCreateDTO) {
        if (subjectCreateDTO.getParentId() == null &&
                subjectCreateDTO.getSurveyEditionId() == null &&
                subjectCreateDTO.getTitle() == null) {
            throw new RuntimeException("No data provided to update the subject");
        }
    }


}
