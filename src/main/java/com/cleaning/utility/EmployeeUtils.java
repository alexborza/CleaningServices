package com.cleaning.utility;

import com.cleaning.domain.appointment.*;

import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

public final class EmployeeUtils {

    private static final Integer LUNCH_BREAK_STARTING_HOUR = 12;
    private static final Integer MINIMUM_TIME_ESTIMATION = 2;

    private EmployeeUtils(){}

    public static Map<Long, Set<TimeSlot>> calculateEmployeesAvailableIntervals(List<Long> employeeIds, List<Appointment> appointments, Integer timeEstimation) {
        Map<Long, List<Appointment>> appointmentsByEmployeeIds = getAppointmentsByEmployeeIds(appointments, employeeIds);
        Map<Long, Set<TimeSlot>> employeesAvailableIntervals = new HashMap<>();

        for(Map.Entry<Long, List<Appointment>> entry: appointmentsByEmployeeIds.entrySet()){
            Long employeeId = entry.getKey();
            List<TimeSlot> timeSlots = entry.getValue().stream()
                    .map(Appointment::getTimeSlot)
                    .sorted(Comparator.comparing(TimeSlot::getStartingHour))
                    .collect(Collectors.toList());

            if(timeSlots.isEmpty()) {
                List<Integer> availableHours = Arrays.asList(8, 9, 10, 11, 13, 14, 15, 16, 17);
                Set<TimeSlot> availableIntervals = calculateAvailableIntervals(availableHours, timeEstimation);
                employeesAvailableIntervals.put(employeeId, availableIntervals);
                continue;
            }

            List<Integer> availableHours = calculateAvailableHours(timeSlots);
            Set<TimeSlot> availableIntervals = calculateAvailableIntervals(availableHours, timeEstimation);
            employeesAvailableIntervals.put(employeeId, availableIntervals);
        }

        return employeesAvailableIntervals;
    }

    private static List<Integer> calculateAvailableHours(List<TimeSlot> timeSlots) {
        List<Integer> hours = Arrays.asList(8, 9, 10, 11, 13, 14, 15, 16, 17);
        Integer firstHourOfWorkingHours = hours.get(0);
        Integer lastHourOfWorkingHours = hours.get(hours.size() - 1);

        for (TimeSlot timeSlot : timeSlots) {
            Integer startingHour = timeSlot.getStartingHour();
            Integer endingHour = timeSlot.getEndingHour();

            if(startingHour.equals(firstHourOfWorkingHours) && !endingHour.equals(lastHourOfWorkingHours)){
                List<Integer> bookedHours = IntStream.range(startingHour, endingHour)
                        .boxed()
                        .collect(Collectors.toList());
                hours.removeAll(bookedHours);
            } else if(endingHour.equals(lastHourOfWorkingHours) && !startingHour.equals(firstHourOfWorkingHours)) {
                List<Integer> bookedHours = IntStream.range(startingHour + 1, endingHour + 1)
                        .boxed()
                        .collect(Collectors.toList());
                hours.removeAll(bookedHours);
            } else {
                hours = new ArrayList<>();
            }

            firstHourOfWorkingHours = hours.get(0);
            lastHourOfWorkingHours = hours.get(hours.size() - 1);
        }

        return hours;
    }

    private static Set<TimeSlot> calculateAvailableIntervals(List<Integer> availableHours, Integer timeEstimation) {
        Set<TimeSlot> availableIntervals = new TreeSet<>();
        calculateAvailableLeftEndInterval(availableIntervals, availableHours, timeEstimation);
        calculateAvailableRightEndInterval(availableIntervals, availableHours, timeEstimation);

        return availableIntervals;
    }

    private static void calculateAvailableLeftEndInterval(Set<TimeSlot> availableIntervals, List<Integer> availableHours, Integer timeEstimation) {
        Integer firstHourOfInterval = availableHours.get(0);
        Integer lastHourOfInterval = availableHours.get(availableHours.size() - 1);

        for(int firstHour = firstHourOfInterval; firstHour < lastHourOfInterval - timeEstimation; firstHour++){

            if(firstHour == LUNCH_BREAK_STARTING_HOUR){
                continue;
            }

            List<Integer> availableHoursRange = IntStream.range(firstHour, firstHour + timeEstimation + 1)
                    .boxed()
                    .collect(Collectors.toList());

            if(availableHours.containsAll(availableHoursRange)) {
                TimeSlot timeSlot = new TimeSlot(firstHour, firstHour + timeEstimation);
                availableIntervals.add(timeSlot);
                break;
            }
        }
    }

    private static void calculateAvailableRightEndInterval(Set<TimeSlot> availableIntervals, List<Integer> availableHours, Integer timeEstimation) {
        Integer lastHourOfInterval = availableHours.get(availableHours.size() - 1);

        for(int lastHour = lastHourOfInterval; lastHour > LUNCH_BREAK_STARTING_HOUR + MINIMUM_TIME_ESTIMATION; lastHour--){
            List<Integer> availableHoursRange = IntStream.range(lastHour - timeEstimation, lastHour + 1)
                    .boxed()
                    .collect(Collectors.toList());

            if(availableHours.containsAll(availableHoursRange)) {
                TimeSlot timeSlot = new TimeSlot(lastHour - timeEstimation, lastHour);
                availableIntervals.add(timeSlot);
                break;
            }
        }
    }

    private static Map<Long, List<Appointment>> getAppointmentsByEmployeeIds(List<Appointment> appointments, List<Long> employeeIds) {
        Map<Long, List<Appointment>> appointmentsByEmployeeId = appointments.stream()
                .collect(groupingBy(ap -> ap.getEmployee().getId()));

        employeeIds.forEach(employeeId -> appointmentsByEmployeeId.computeIfAbsent(employeeId, k -> new ArrayList<>()));

        return appointmentsByEmployeeId;
    }
}
