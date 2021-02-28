package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.validators.CustomerValidator;
import com.udacity.jdnd.course3.critter.validators.EmployeeValidator;
import com.udacity.jdnd.course3.critter.validators.PetValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    ScheduleRepository scheduleRepository;
    PetRepository petRepository;
    EmployeeRepository employeeRepository;
    CustomerRepository customerRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,
                               PetRepository petRepository,
                               EmployeeRepository employeeRepository,
                               CustomerRepository customerRepository) {
        this.scheduleRepository = scheduleRepository;
        this.petRepository = petRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getEmployeeSchedule(Long employeeId) {
        EmployeeValidator.validateEmployeeId(employeeId);
        Employee employee = employeeRepository.getOne(employeeId);
        if (Objects.isNull(employee)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employees Found");
        }
        List<Schedule> scheduleList = scheduleRepository.findByEmployee(employee);
        if (Objects.isNull(scheduleList) || scheduleList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Schedule Found");
        }
        return scheduleList;
    }

    @Override
    public List<Schedule> getPetSchedule(Long petId) {
        PetValidator.validatePetId(petId);
        Pet pet = petRepository.getOne(petId);
        if (Objects.isNull(pet)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Pets Found");
        }
        List<Schedule> scheduleList = scheduleRepository.findByPets(pet);
        if (Objects.isNull(scheduleList) || scheduleList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Schedule Found");
        }
        return scheduleList;
    }

    @Override
    public List<Schedule> getCustomerSchedule(Long customerId) {
        CustomerValidator.validateCustomerId(customerId);
        Customer customer = customerRepository.getOne(customerId);
        if (Objects.isNull(customer)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Customer Found");
        }
        List<Schedule> scheduleList = scheduleRepository.findByPetsIn(customer.getPets());
        if (Objects.isNull(scheduleList) || scheduleList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Schedule Found");
        }
        return scheduleList;
    }

    @Override
    public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Pet> pets = petRepository.findAllById(petIds);
        if (Objects.isNull(pets) || pets.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Pets Found");
        }
        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        if (Objects.isNull(employees) || employees.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Pets Found");
        }
        schedule.setPets(pets);
        schedule.setEmployee(employees);
        return scheduleRepository.save(schedule);
    }
}
