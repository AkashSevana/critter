package com.udacity.jdnd.course3.critter.utils;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;

public class EmployeeUtil {

    public static EmployeeDTO employeeToEmployeeDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .daysAvailable(employee.getDaysAvailable())
                .skills(employee.getSkills())
                .build();
    }
}
