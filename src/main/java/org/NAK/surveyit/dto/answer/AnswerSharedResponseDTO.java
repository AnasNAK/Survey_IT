package org.NAK.surveyit.dto.answer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerSharedResponseDTO {

    private Long id;
    private String text;
    private Integer selectionCount;

}