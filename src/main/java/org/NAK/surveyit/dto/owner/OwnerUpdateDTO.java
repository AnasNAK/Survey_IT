package org.NAK.surveyit.dto.owner;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerUpdateDTO {


    @NotBlank(message = "Name is required")
    private String name;
}
