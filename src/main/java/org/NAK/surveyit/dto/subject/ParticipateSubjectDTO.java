package org.NAK.surveyit.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipateSubjectDTO {


        private String title;
        private List<SubSubjectDTO> subSubjects;
    }
