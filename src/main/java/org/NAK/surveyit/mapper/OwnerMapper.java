package org.NAK.surveyit.mapper;

import org.NAK.surveyit.dto.owner.OwnerCreateDTO;
import org.NAK.surveyit.dto.owner.OwnerResponseDTO;
import org.NAK.surveyit.dto.owner.OwnerUpdatDTO;
import org.NAK.surveyit.entity.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    OwnerResponseDTO toResponseDTO(Owner owner);

    Owner toEntity(OwnerCreateDTO ownerCreateDTO);

    @Mapping(target = "id",source = "ownerUpdateDTO.id")
    Owner toEntity(OwnerUpdatDTO ownerUpdateDTO);

}
