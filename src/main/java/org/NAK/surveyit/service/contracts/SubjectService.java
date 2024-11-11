package org.NAK.surveyit.service.contracts;

import org.NAK.surveyit.dto.subject.MainSubjectResponse;
import org.NAK.surveyit.dto.subject.SubjectCreateDTO;
import org.NAK.surveyit.dto.subject.SubjectResponseDTO;
import org.NAK.surveyit.dto.subject.SubjectSharedResponseDTO;

import java.util.List;

public interface SubjectService {
    SubjectResponseDTO createSubject(SubjectCreateDTO subjectCreateDTO);
    List<SubjectResponseDTO> findAllSubjects();
    List<MainSubjectResponse> findAllMainSubjects();

    Object findSubjectById(Long id);

    boolean deleteById(Long id);


    Object updateSubject(Long id , SubjectCreateDTO subjectCreateDTO);
}
