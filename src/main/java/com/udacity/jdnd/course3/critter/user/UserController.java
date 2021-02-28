package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.utils.CustomerUtil;
import com.udacity.jdnd.course3.critter.utils.EmployeeUtil;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private CustomerService customerService;

    private EmployeeService employeeService;

    public UserController(CustomerService customerService, EmployeeService employeeService) {
        this.customerService = customerService;
        this.employeeService = employeeService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = Customer.builder()
                .id(customerDTO.getId())
                .name(customerDTO.getName())
                .notes(customerDTO.getNotes())
                .phoneNumber(customerDTO.getPhoneNumber())
                .build();
        return CustomerUtil.customerToCustomerDTO(customerService.saveCustomer(customer, customerDTO.getPetIds()));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers()
                .stream()
                .map(customer -> CustomerUtil.customerToCustomerDTO(customer))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return CustomerUtil.customerToCustomerDTO(customerService.getCustomerByPetId(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = Employee.builder()
                .id(employeeDTO.getId())
                .name(employeeDTO.getName())
                .daysAvailable(employeeDTO.getDaysAvailable())
                .skills(employeeDTO.getSkills())
                .build();
        return EmployeeUtil.employeeToEmployeeDTO(employeeService.saveEmployee(employee));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return EmployeeUtil.employeeToEmployeeDTO(employeeService.getEmployeeById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setEmployeeAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return employeeService.getEmployeesByService(employeeDTO.getDate(), employeeDTO.getSkills())
                .stream()
                .map(employee -> EmployeeUtil.employeeToEmployeeDTO(employee))
                .collect(Collectors.toList());
    }

}
