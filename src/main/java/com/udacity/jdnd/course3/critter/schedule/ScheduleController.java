package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.utils.ScheduleUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = Schedule.builder()
                .id(scheduleDTO.getId())
                .date(scheduleDTO.getDate())
                .activities(scheduleDTO.getActivities())
                .build();
        return ScheduleUtil.scheduleToScheduleDTO(scheduleService.saveSchedule(schedule, scheduleDTO.getEmployeeIds(), scheduleDTO.getPetIds()));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAllSchedules()
                .stream()
                .map(schedule -> ScheduleUtil.scheduleToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.getPetSchedule(petId)
                .stream()
                .map(schedule -> ScheduleUtil.scheduleToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.getEmployeeSchedule(employeeId)
                .stream()
                .map(schedule -> ScheduleUtil.scheduleToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.getCustomerSchedule(customerId)
                .stream()
                .map(schedule -> ScheduleUtil.scheduleToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }
}
