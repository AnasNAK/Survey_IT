package org.NAK.surveyit.mapper;

import org.NAK.surveyit.dto.owner.OwnerCreateDTO;
import org.NAK.surveyit.dto.owner.OwnerResponseDTO;
import org.NAK.surveyit.dto.owner.OwnerUpdateDTO;
import org.NAK.surveyit.entity.Owner;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface OwnerMapper {

    OwnerResponseDTO toResponseDTO(Owner owner);

    Owner toEntity(OwnerCreateDTO ownerCreateDTO);

    Owner toEntity(OwnerUpdateDTO ownerUpdateDTO);

}
