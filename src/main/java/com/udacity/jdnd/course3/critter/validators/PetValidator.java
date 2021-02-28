package com.udacity.jdnd.course3.critter.validators;

import com.udacity.jdnd.course3.critter.entity.Pet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Slf4j
public final class PetValidator {

    public static void validatePet(Pet pet) {
        if (Objects.isNull(pet)
                || Objects.isNull(pet.getId())
                || Objects.isNull(pet.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Pet details");
        }
    }

    public static void validatePetId(Long petId) {
        if (Objects.isNull(petId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Pet id");
        }
    }
}
