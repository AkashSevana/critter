package com.udacity.jdnd.course3.critter.utils;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;

import java.util.stream.Collectors;

public final class ScheduleUtil {

    public static ScheduleDTO scheduleToScheduleDTO(Schedule schedule) {
        return ScheduleDTO.builder()
                .id(schedule.getId())
                .petIds(schedule.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()))
                .employeeIds(schedule.getEmployee().stream().map(employee -> employee.getId()).collect(Collectors.toList()))
                .date(schedule.getDate())
                .activities(schedule.getActivities())
                .build();
    }
}