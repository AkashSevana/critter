package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public interface EmployeeService {
    List<Employee> getEmployeesByService(LocalDate date, Set<EmployeeSkill> skills);
    Employee getEmployeeById(Long employeeId);
    void setEmployeeAvailability(Set<DayOfWeek> days, Long employeeId);
    Employee saveEmployee(Employee employee);
}
