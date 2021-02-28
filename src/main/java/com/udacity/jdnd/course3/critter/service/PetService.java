package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetService {
    Pet savePet(Pet pet, Long customerId);
    Pet getPetById(Long petId);
    List<Pet> getPetsByCustomerId(Long customerId);
    List<Pet> getAllPets();
}
