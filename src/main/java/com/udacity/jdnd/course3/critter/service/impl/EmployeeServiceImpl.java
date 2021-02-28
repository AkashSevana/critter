package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.validators.EmployeeValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getEmployeesByService(LocalDate date, Set<EmployeeSkill> skills) {
        List<Employee> employees = employeeRepository
                .findByDaysAvailable(date.getDayOfWeek()).stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
        if (Objects.isNull(employees) || employees.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employees Found");
        }
        return employees;
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        EmployeeValidator.validateEmployeeId(employeeId);
        Employee employee = employeeRepository.getOne(employeeId);
        if (Objects.isNull(employee)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employees Found");
        }
        return employee;
    }

    @Override
    public void setEmployeeAvailability(Set<DayOfWeek> days, Long employeeId) {
        EmployeeValidator.validateEmployeeId(employeeId);
        Employee employee = employeeRepository.getOne(employeeId);
        if (Objects.isNull(employee)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employees Found");
        }
        employee.setDaysAvailable(days);
        employeeRepository.save(employee);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        EmployeeValidator.validateEmployee(employee);
        return employeeRepository.save(employee);
    }
}
