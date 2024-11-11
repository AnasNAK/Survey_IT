package org.NAK.surveyit.mapper;

import org.NAK.surveyit.dto.subject.MainSubjectResponse;
import org.NAK.surveyit.dto.subject.SubjectCreateDTO;
import org.NAK.surveyit.dto.subject.SubjectResponseDTO;
import org.NAK.surveyit.dto.subject.SubjectSharedResponseDTO;
import org.NAK.surveyit.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject toSubject(SubjectCreateDTO subjectCreateDTO);

    @Mapping(source = "parent" , target = "parent")
    @Mapping(source = "surveyEdition" , target = "surveyEdition")
    @Mapping(source = "questionList" , target = "questionList")
    SubjectResponseDTO toResponseSubject(Subject subject);

    SubjectSharedResponseDTO toSubSubject(Subject subject);

    MainSubjectResponse toMainSubjectResponse(Subject subject);

}
