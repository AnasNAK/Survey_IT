package org.NAK.surveyit.dto.owner;

import jakarta.validation.constraints.NotBlank;
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
public class OwnerUpdateDTO {


    @NotBlank(message = "Name is required")
    @Unique(message = "Owner name must be unique",entity = Owner.class, repository = OwnerRepository.class ,  column = "name")
    private String name;
}
