package com.udacity.jdnd.course3.critter.validators;

import com.udacity.jdnd.course3.critter.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

public final class EmployeeValidator {

    public static void validateEmployeeId(Long employeeId) {
        if (Objects.isNull(employeeId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Employee id");
        }
    }

    public static void validateEmployee(Employee employee) {
        if (Objects.isNull(employee)
                || Objects.isNull(employee.getId())
                || Objects.isNull(employee.getName())
                || Objects.isNull(employee.getSkills())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Employee details");
        }
    }
}
