package org.NAK.surveyit.service.contracts;

import org.NAK.surveyit.dto.owner.OwnerCreateDTO;
import org.NAK.surveyit.dto.owner.OwnerResponseDTO;
import org.NAK.surveyit.dto.owner.OwnerUpdateDTO;

import java.util.List;

public interface OwnerService {
    List<OwnerResponseDTO> findAllOwners();
    OwnerResponseDTO findOwnerById(long id);
    OwnerResponseDTO saveOwner(OwnerCreateDTO ownerCreateDTO);
    OwnerResponseDTO updateOwner(OwnerUpdateDTO ownerUpdatDTO , long id);
    void deleteOwner(long id);

    OwnerResponseDTO findByName(String name);
}
