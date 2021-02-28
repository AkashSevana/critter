package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {
    List<Schedule> getAllSchedules();
    List<Schedule> getEmployeeSchedule(Long employeeId);
    List<Schedule> getPetSchedule(Long petId);
    List<Schedule> getCustomerSchedule(Long customerId);
    Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds);
}
