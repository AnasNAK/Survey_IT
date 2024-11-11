package org.NAK.surveyit.mapper;

import org.NAK.surveyit.dto.owner.OwnerCreateDTO;
import org.NAK.surveyit.dto.owner.OwnerResponseDTO;
import org.NAK.surveyit.dto.owner.OwnerUpdateDTO;
import org.NAK.surveyit.entity.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface OwnerMapper {

    @Mapping(source="surveys" ,target = "surveys")
    OwnerResponseDTO toResponseDTO(Owner owner);

    Owner toEntity(OwnerCreateDTO ownerCreateDTO);

    Owner toEntity(OwnerUpdateDTO ownerUpdateDTO);

}
