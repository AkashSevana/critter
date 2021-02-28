package com.udacity.jdnd.course3.critter.utils;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;

public class PetUtil {

    public static PetDTO petToPetDTO(Pet pet) {
        return PetDTO.builder()
                .id(pet.getId())
                .name(pet.getName())
                .birthDate(pet.getBirthDate())
                .type(pet.getType())
                .ownerId(pet.getCustomer().getId())
                .notes(pet.getNotes())
                .build();
    }
}
