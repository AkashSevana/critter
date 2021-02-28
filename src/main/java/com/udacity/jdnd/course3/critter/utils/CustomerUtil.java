package com.udacity.jdnd.course3.critter.utils;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;

import java.util.stream.Collectors;

public class CustomerUtil {

    public static CustomerDTO customerToCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .notes(customer.getNotes())
                .petIds(customer.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()))
                .build();
    }
}
