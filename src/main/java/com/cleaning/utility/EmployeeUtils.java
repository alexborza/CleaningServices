package com.cleaning.utility;

import com.cleaning.domain.appointment.*;

import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

public final class EmployeeUtils {

    private static final Integer LUNCH_BREAK_STARTING_HOUR = 12;
    private static final Integer LUNCH_BREAK_ENDING_HOUR = 13;
    private static final List<Integer> lunchBreakHours = List.of(12, 13);

    private EmployeeUtils(){}

    public static Map<Long, Set<TimeSlot>> calculateEmployeesAvailableIntervals(List<Long> employeeIds, List<Appointment> appointments, Integer timeEstimation) {
        Map<Long, List<TimeSlot>> timeSlotsByEmployeeIds = getTimeSlotsByEmployeeIds(appointments, employeeIds);
        Map<Long, Set<TimeSlot>> employeesAvailableIntervals = new HashMap<>();

        for(Map.Entry<Long, List<TimeSlot>> entry: timeSlotsByEmployeeIds.entrySet()){
            Long employeeId = entry.getKey();
            List<TimeSlot> timeSlots = entry.getValue();

            if(timeSlots.isEmpty()) {
                List<Integer> availableHours = new ArrayList<>(Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16, 17));
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

    private static Map<Long, List<TimeSlot>> getTimeSlotsByEmployeeIds(List<Appointment> appointments, List<Long> employeeIds) {
        Map<Long, List<TimeSlot>> timeSlotsByEmployeeId = appointments.stream()
                .collect(groupingBy(ap -> ap.getEmployee().getId(),
                        mapping(Appointment::getTimeSlot, Collectors.toList())));

        employeeIds.forEach(employeeId -> timeSlotsByEmployeeId.computeIfAbsent(employeeId, k -> new ArrayList<>()));

        return timeSlotsByEmployeeId;
    }

    private static Set<TimeSlot> calculateAvailableIntervals(List<Integer> availableHours, Integer timeEstimation) {
        if(availableHours.isEmpty())
            return new TreeSet<>();

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

            List<Integer> availableHoursRange = generateRangeInclusive(firstHour, firstHour + timeEstimation);

            if(availableHours.containsAll(availableHoursRange)) {
                Integer lastHourFromRange = availableHoursRange.get(availableHoursRange.size() - 1);

                if(availableHoursRange.containsAll(lunchBreakHours) && availableHours.contains(lastHourFromRange + 1)){
                    addIntervalTo(availableIntervals, firstHour, lastHourFromRange + 1);
                    break;
                }

                addIntervalTo(availableIntervals, firstHour, firstHour + timeEstimation);
                break;
            }
        }
    }

    private static void calculateAvailableRightEndInterval(Set<TimeSlot> availableIntervals, List<Integer> availableHours, Integer timeEstimation) {
        Integer firstHourOfInterval = availableHours.get(0);
        Integer lastHourOfInterval = availableHours.get(availableHours.size() - 1);

        for(int lastHour = lastHourOfInterval; lastHour > firstHourOfInterval + timeEstimation; lastHour--){
            if(lastHour == LUNCH_BREAK_ENDING_HOUR){
                continue;
            }

            List<Integer> availableHoursRange = generateRangeInclusive(lastHour - timeEstimation, lastHour);

            if(availableHours.containsAll(availableHoursRange)) {
                Integer firstHourFromRange = availableHoursRange.get(0);

                if(availableHoursRange.containsAll(lunchBreakHours) && availableHours.contains(firstHourFromRange -1)) {
                    addIntervalTo(availableIntervals, firstHourFromRange -1, lastHour);
                    break;
                }

                addIntervalTo(availableIntervals, firstHourFromRange, lastHour);
                break;
            }
        }
    }

    private static void addIntervalTo(Set<TimeSlot> availableIntervals, Integer from, Integer to) {
        TimeSlot timeSlot = new TimeSlot(from, to);
        availableIntervals.add(timeSlot);
    }

    private static List<Integer> generateRangeInclusive(int from, int to) {
        return IntStream.range(from, to + 1)
                .boxed()
                .collect(Collectors.toList());
    }

    private static List<Integer> calculateAvailableHours(List<TimeSlot> timeSlots) {
        List<Integer> hours = new ArrayList<>(Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16, 17));
        Integer firstHourOfWorkingHours = hours.get(0);
        Integer lastHourOfWorkingHours = hours.get(hours.size() - 1);

        for (TimeSlot timeSlot : timeSlots) {
            Integer startingHour = timeSlot.getStartingHour();
            Integer endingHour = timeSlot.getEndingHour();
            List<Integer> bookedHours = new ArrayList<>();

            if(startingHour.equals(firstHourOfWorkingHours) && endingHour.equals(lastHourOfWorkingHours)) {
                return bookedHours;
            } else if(startingHour.equals(firstHourOfWorkingHours)){
                bookedHours = generateRangeInclusive(startingHour, endingHour - 1);
            } else if(endingHour.equals(lastHourOfWorkingHours)) {
                bookedHours = generateRangeInclusive(startingHour + 1, endingHour);
            }

            hours.removeAll(bookedHours);
            firstHourOfWorkingHours = hours.get(0).equals(12) ? hours.get(1) : hours.get(0);
            lastHourOfWorkingHours = hours.get(hours.size() - 1);
        }

        return hours;
    }
}
