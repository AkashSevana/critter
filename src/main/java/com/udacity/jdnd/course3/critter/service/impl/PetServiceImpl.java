package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.validators.CustomerValidator;
import com.udacity.jdnd.course3.critter.validators.PetValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class PetServiceImpl implements PetService {

    private PetRepository petRepository;
    private CustomerRepository customerRepository;

    public PetServiceImpl(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Pet savePet(Pet pet, Long customerId) {
        PetValidator.validatePet(pet);
        CustomerValidator.validateCustomerId(customerId);
        Customer customer = customerRepository.getOne(customerId);
        if (Objects.isNull(customer)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        pet.setCustomer(customer);
        pet = petRepository.save(pet);
        List<Pet> pets = customer.getPets();
        if (Objects.isNull(pets)) {
            pets = new ArrayList<>();
        }
        pets.add(pet);
        customer.setPets(pets);
        customerRepository.save(customer);

        return pet;
    }

    @Override
    public Pet getPetById(Long petId) {
        PetValidator.validatePetId(petId);
        Pet pet = petRepository.getOne(petId);
        if (Objects.isNull(pet)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found");
        }
        return pet;
    }

    @Override
    public List<Pet> getPetsByCustomerId(Long customerId) {
        CustomerValidator.validateCustomerId(customerId);
        List<Pet> pets = petRepository.findPetByCustomerId(customerId);
        if (Objects.isNull(pets) || pets.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found");
        }
        return pets;
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }


}
