package org.NAK.surveyit.dto.owner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OwnerCreateDTO {

    @NotBlank(message = "Owner name cannot be blank")
    @Size(max = 50, message = "Owner name must be less than 50 characters")
    private String name;

}
