package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.utils.PetUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = Pet.builder()
                .id(petDTO.getId())
                .name(petDTO.getName())
                .notes(petDTO.getNotes())
                .type(petDTO.getType())
                .birthDate(petDTO.getBirthDate())
                .build();
        return PetUtil.petToPetDTO(petService.savePet(pet, petDTO.getOwnerId()));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return PetUtil.petToPetDTO(petService.getPetById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.getAllPets()
                .stream()
                .map(pet -> PetUtil.petToPetDTO(pet))
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.getPetsByCustomerId(ownerId)
                .stream()
                .map(pet -> PetUtil.petToPetDTO(pet))
                .collect(Collectors.toList());
    }
}
