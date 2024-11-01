package org.NAK.surveyit.service.implementations;

import org.NAK.surveyit.dto.owner.OwnerCreateDTO;
import org.NAK.surveyit.dto.owner.OwnerResponseDTO;
import org.NAK.surveyit.dto.owner.OwnerUpdatDTO;
import org.NAK.surveyit.entity.Owner;
import org.NAK.surveyit.mapper.OwnerMapper;
import org.NAK.surveyit.repository.OwnerRepository;
import org.NAK.surveyit.service.contracts.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository, OwnerMapper ownerMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
    }


    @Override
    public List<OwnerResponseDTO> findAllOwners() {

        return  ownerRepository.findAll()
                .stream()
                .map(ownerMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OwnerResponseDTO findOwnerById(long id) {

        return ownerRepository.findById(id)
                .map(ownerMapper::toResponseDTO)
                .orElse(null);

    }

    @Override
    public OwnerResponseDTO saveOwner(OwnerCreateDTO ownerCreateDTO) {
        Owner owner = ownerMapper.toEntity(ownerCreateDTO);
        Owner savedOwner = ownerRepository.save(owner);
        return ownerMapper.toResponseDTO(savedOwner);

    }

    @Override
    public OwnerResponseDTO updateOwner(OwnerUpdatDTO ownerUpdatDTO , long id) {
        return null;
    }

    @Override
    public void deleteOwner(long id) {

    }
}
