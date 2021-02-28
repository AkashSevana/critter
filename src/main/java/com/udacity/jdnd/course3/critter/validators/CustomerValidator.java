package com.udacity.jdnd.course3.critter.validators;

import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

public final class CustomerValidator {

    public static void validateCustomer(Customer customer) {
        if (Objects.isNull(customer)
                || Objects.isNull(customer.getId())
                || Objects.isNull(customer.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Customer details");
        }
    }

    public static void validateCustomerId(Long customerId) {
        if (Objects.isNull(customerId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Customer id");
        }
    }
}
