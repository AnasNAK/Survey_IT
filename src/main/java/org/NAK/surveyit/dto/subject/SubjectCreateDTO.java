package org.NAK.surveyit.dto.subject;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.NAK.surveyit.annotation.EXIST.Exist;
import org.NAK.surveyit.annotation.UNIQUE.Unique;
import org.NAK.surveyit.entity.Subject;
import org.NAK.surveyit.entity.SurveyEdition;
import org.NAK.surveyit.repository.SubjectRepository;
import org.NAK.surveyit.repository.SurveyEditionRepository;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectCreateDTO {

    @NotBlank
    @Unique(entity = Subject.class, repository = SubjectRepository.class, column = "Title")
    private String title;

    private Long parentId;

    @Exist(entity = SurveyEdition.class, repository = SurveyEditionRepository.class)
    private Long surveyEditionId;

}
