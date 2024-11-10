package org.NAK.surveyit.controller;

import jakarta.validation.Valid;
import org.NAK.surveyit.annotation.EXIST.Exist;
import org.NAK.surveyit.dto.owner.OwnerCreateDTO;
import org.NAK.surveyit.dto.owner.OwnerResponseDTO;
import org.NAK.surveyit.dto.owner.OwnerUpdateDTO;
import org.NAK.surveyit.entity.Owner;
import org.NAK.surveyit.repository.OwnerRepository;
import org.NAK.surveyit.service.contracts.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<OwnerResponseDTO> getAllOwners() {
        return ownerService.findAllOwners();
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<OwnerResponseDTO> getOwnerById(
            @PathVariable("ownerId")
            @Exist(message = "Owner with ID ${validatedValue} does not exist", entity = Owner.class, repository = OwnerRepository.class) Long id) {

        OwnerResponseDTO owner = ownerService.findOwnerById(id); // This line will only run if validation passes
        return ResponseEntity.ok(owner);
    }


    @PostMapping
    public ResponseEntity<OwnerResponseDTO> createOwner(@Valid @RequestBody OwnerCreateDTO ownerCreateDTO) {
        OwnerResponseDTO createdOwner = ownerService.saveOwner(ownerCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOwner);
    }


    @PutMapping("/{ownerId}")
    public ResponseEntity<OwnerResponseDTO> updateOwner(
            @PathVariable("ownerId")
            @Exist(message = "Owner with Id ${validatedValue} does not exist" , entity = Owner.class , repository = OwnerRepository.class) Long id,
             @Valid @RequestBody OwnerUpdateDTO ownerUpdateDTO){

        OwnerResponseDTO updatedOwner = ownerService.updateOwner(ownerUpdateDTO,id);
        return  ResponseEntity.ok(updatedOwner);
    }
    @DeleteMapping("/{ownerId}")
            public  ResponseEntity<Void> deleteOwner(@PathVariable("ownerId")
                       @Exist(message = "Owner with ID ${ValidatedValue} does not exist" ,entity = Owner.class,repository = OwnerRepository.class) Long id){

        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name/{ownerName}")
    public ResponseEntity<OwnerResponseDTO> getOwnerByName(@PathVariable("ownerName") String name){
        OwnerResponseDTO owner = ownerService.findByName(name);
        return ResponseEntity.ok(owner);
    }



}
