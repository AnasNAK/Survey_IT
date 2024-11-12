package org.NAK.surveyit.service.implementations;

import org.NAK.surveyit.dto.owner.OwnerResponseDTO;
import org.NAK.surveyit.entity.Owner;
import org.NAK.surveyit.mapper.OwnerMapper;
import org.NAK.surveyit.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OwnerServiceImplTest {

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private OwnerMapper ownerMapper;

    @InjectMocks
    private OwnerServiceImpl ownerService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void findAllOwners() {
        Owner owner1 = new Owner(1L, "anas nak" , List.of());
        Owner owner2 = new Owner(2L, "ahmed nak" , List.of());
        List<Owner> owners = Arrays.asList(owner1, owner2);

        OwnerResponseDTO responseDTO1 = new OwnerResponseDTO(1L, "anas nak" , List.of());
        OwnerResponseDTO responseDTO2 = new OwnerResponseDTO(2L, "ahmed nak" ,List.of());
        List<OwnerResponseDTO> expectedResponse = Arrays.asList(responseDTO1, responseDTO2);

        when(ownerRepository.findAll()).thenReturn(owners);
        when(ownerMapper.toResponseDTO(owner1)).thenReturn(responseDTO1);
        when(ownerMapper.toResponseDTO(owner2)).thenReturn(responseDTO2);

        List<OwnerResponseDTO> actualResponse = ownerService.findAllOwners();

        assertEquals(expectedResponse, actualResponse);
    }
}
