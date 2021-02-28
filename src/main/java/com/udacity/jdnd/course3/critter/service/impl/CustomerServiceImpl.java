package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.validators.CustomerValidator;
import com.udacity.jdnd.course3.critter.validators.PetValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    PetRepository petRepository;
    CustomerRepository customerRepository;

    public CustomerServiceImpl(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomerByPetId(Long petId) {
        PetValidator.validatePetId(petId);
        Customer customer = petRepository.getOne(petId).getCustomer();
        if (Objects.isNull(customer)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer, List<Long> petIds) {
        CustomerValidator.validateCustomer(customer);
        List<Pet> customerPetList = new ArrayList<>();
        if (Objects.nonNull(petIds) && !petIds.isEmpty()) {
            petIds.stream().forEach(id -> PetValidator.validatePetId(id));
            customerPetList = petIds.stream().map(id -> petRepository.getOne(id)).collect(Collectors.toList());
            if (Objects.isNull(customerPetList) || customerPetList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Pets empty");
            }
        }
        customer.setPets(customerPetList);
        return customerRepository.save(customer);
    }
}
