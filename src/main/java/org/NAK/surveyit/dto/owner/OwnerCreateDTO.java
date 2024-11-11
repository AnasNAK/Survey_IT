package org.NAK.surveyit.dto.owner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.NAK.surveyit.annotation.UNIQUE.Unique;
import org.NAK.surveyit.entity.Owner;
import org.NAK.surveyit.repository.OwnerRepository;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerCreateDTO {

    @NotBlank(message = "Owner name cannot be blank")
    @Size(max = 50, message = "Owner name must be less than 50 characters")
    @Unique(message = "Owner name must be unique", entity = Owner.class, repository = OwnerRepository.class, column = "name")
    private String name;

}
