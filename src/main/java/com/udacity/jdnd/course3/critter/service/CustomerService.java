package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    Customer getCustomerByPetId(Long petId);
    List<Customer> getAllCustomers();
    Customer saveCustomer(Customer customer, List<Long> petIds);
}
