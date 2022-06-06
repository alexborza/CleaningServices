package com.cleaning.entity;

import com.cleaning.facade.dto.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.*;
import java.time.temporal.*;
import java.util.*;

@Entity
@Table(name = "CLEANING_SERVICE")
@Getter
@Setter
@NoArgsConstructor
public class CleaningService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private ContactInfo contactInfo;

    @Embedded
    private Location location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cleaning_details_id", referencedColumnName = "id")
    private CleaningDetails cleaningDetails;

    @Enumerated(EnumType.STRING)
    private CleaningFrequency cleaningFrequency;

    @Embedded
    private CleaningDate cleaningDate;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private double total;

    @Enumerated(EnumType.STRING)
    private CleaningServiceType type;

    private int timeEstimation;

    @ManyToOne(fetch = FetchType.LAZY)
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private CleaningStatus status;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "dates_of_cleaning", joinColumns = @JoinColumn(name = "cleaning_service_id"))
    private List<CleaningDate> datesOfCleaning = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "messages", joinColumns = @JoinColumn(name = "cleaning_service_id"))
    private List<Message> messages = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "rescheduled_dates", joinColumns = @JoinColumn(name = "cleaning_service_id"))
    private List<RescheduleDate> rescheduledDates = new ArrayList<>();

    public String getEmployeeName(){
        return employee.getUserInformation().getFullName();
    }

    public void finishCleaning(){
        if(cleaningFrequency == null || cleaningFrequency == CleaningFrequency.OneTime)
            status = CleaningStatus.Finished;
    }

    public void addDateOfCleaning(CleaningDate futureCleaningDate){
        this.datesOfCleaning.add(futureCleaningDate);
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void addRescheduledDate(RescheduleDate rescheduleDate) {
        this.rescheduledDates.add(rescheduleDate);
    }

    public String getNextCleaningDate(){
        String nextCleaningDate = getNextCleaningDateNoReschedule();
        if(nextCleaningDate == null)
            return null;
        if(!isDateRescheduled(nextCleaningDate))
            return nextCleaningDate;
        return findRescheduledDateForDate(nextCleaningDate);
    }


    public boolean isDateRescheduled(String date){
        return this.rescheduledDates.stream()
                .anyMatch(rd -> rd.getDateToReschedule().equals(date));
    }

    public boolean isDateCanceled(String date){
        Optional<RescheduleDate> rescheduleDate = this.rescheduledDates.stream()
                .filter(rd -> rd.getDateToReschedule().equals(date))
                .findFirst();
        if(rescheduleDate.isEmpty())
            return false;
        return rescheduleDate.map(RescheduleDate::getRescheduledDate).isEmpty();
    }

    public boolean isDateFinished(String date) {
        if(isDateRescheduled(date))
            date = findRescheduledDateForDate(date);
        String finalDate = date;
        return datesOfCleaning.stream()
                .map(CleaningDate::getCleaningDate)
                .anyMatch(cleaningDate -> cleaningDate.equals(finalDate));
    }

    public boolean isDateFutureCleaningDate(String date, String frequency) {
        LocalDate firstCleaningDate = getFirstCleaningDate();
        LocalDate nextCleaningDate = LocalDate.parse(date);
        long days = ChronoUnit.DAYS.between(firstCleaningDate, nextCleaningDate);
        return areServicesOverlapped(days, frequency);
    }

    public int getStartingHour(){
        return cleaningDate.getStartingHour();
    }

    public int getFinishingHour(){
        return cleaningDate.getFinishingHour();
    }

    public List<DatesToRescheduleDto> getDatesToReschedule(){
        String date = getNextCleaningDateNoReschedule();
        return getDatesToReschedule(date);
    }

    public CleaningDate getDateOfCleaning(String date){
        if(date == null)
            return null;

        RescheduleDate rescheduleDate = rescheduledDates.stream()
                .filter(rd -> date.equals(rd.getRescheduledDate()))
                .findFirst()
                .orElse(null);
        if(rescheduleDate == null)
            return new CleaningDate(date, cleaningDate.getStartingHour(), cleaningDate.getFinishingHour());
        return new CleaningDate(date, rescheduleDate.getStartingHour(), rescheduleDate.getEndingHour());
    }

    private String findRescheduledDateForDate(String date){
        return rescheduledDates.stream()
                .filter(rd -> rd.getDateToReschedule().equals(date))
                .findFirst()
                .map(RescheduleDate::getRescheduledDate)
                .orElse(this.getNextDateByFrequency(date));
    }

    private String getNextCleaningDateNoReschedule(){
        if(status == CleaningStatus.Deleted || status == CleaningStatus.Finished){
            return null;
        }
        return calculateNextDate();
    }

    private String calculateNextDate(){
        LocalDate firstCleaningDate = LocalDate.parse(cleaningDate.getCleaningDate());
        if(cleaningFrequency == null)
            return calculateNextDateByFrequency(firstCleaningDate, 0L);
        switch(cleaningFrequency){
            case OneTime:
                return calculateNextDateByFrequency(firstCleaningDate, 0L);
            case Weekly:
                return calculateNextDateByFrequency(firstCleaningDate, 7L);
            case BiWeekly:
                return calculateNextDateByFrequency(firstCleaningDate, 14L);
            case Monthly:
                return calculateNextDateByFrequency(firstCleaningDate, 28L);
            default:
                return null;
        }
    }

    private String calculateNextDateByFrequency(LocalDate firstCleaningDate, long daysToAdd){
        String nextCleaningDate = firstCleaningDate.plusDays(datesOfCleaning.size() * daysToAdd).toString();
        int i = 1;
        while(isDateCanceled(nextCleaningDate) || isDateFinished(nextCleaningDate)){
            nextCleaningDate = firstCleaningDate.plusDays((datesOfCleaning.size() + i) * daysToAdd).toString();
            i++;
        }
        return nextCleaningDate;
    }

    private List<DatesToRescheduleDto> getDatesToReschedule(String cleaningDate){
        if(cleaningDate == null)
            return Collections.emptyList();

        if(cleaningFrequency == null || cleaningFrequency == CleaningFrequency.OneTime) {
            if(isDateRescheduled(cleaningDate))
                return List.of(new DatesToRescheduleDto(findRescheduledDateForDate(cleaningDate), true));
            return List.of(new DatesToRescheduleDto(cleaningDate, false));
        }

        LocalDate nextDate = LocalDate.parse(cleaningDate);
        switch(cleaningFrequency){
            case Weekly:
                return calculateDatesToReschedule(nextDate, 6, 7);
            case BiWeekly:
                return calculateDatesToReschedule(nextDate, 4, 14);
            case Monthly:
                return calculateDatesToReschedule(nextDate, 3, 28);
            default:
                return Collections.emptyList();
        }
    }

    private List<DatesToRescheduleDto> calculateDatesToReschedule(LocalDate nextDate, int numberOfDates, int daysToAdd){
        List<DatesToRescheduleDto> datesToReschedule = new ArrayList<>();
        for(int i = 0; i < numberOfDates; i++){
            if(isDateCanceled(nextDate.toString()))
                i--;
            addDateToReschedule(datesToReschedule, nextDate.toString());
            nextDate = nextDate.plusDays(daysToAdd);
        }
        return datesToReschedule;
    }

    private void addDateToReschedule(List<DatesToRescheduleDto> datesToReschedule, String nextDate){
        if(isDateRescheduled(nextDate)){
            if(!isDateCanceled(nextDate))
                datesToReschedule.add(new DatesToRescheduleDto(findRescheduledDateForDate(nextDate), true));
        } else {
            datesToReschedule.add(new DatesToRescheduleDto(nextDate, false));
        }
    }

    private LocalDate getFirstCleaningDate(){
        if(!isFrequentlyDone() && isDateRescheduled(cleaningDate.getCleaningDate()))
            return LocalDate.parse(this.rescheduledDates.get(0).getRescheduledDate());
        return LocalDate.parse(cleaningDate.getCleaningDate());
    }

    private boolean isFrequentlyDone(){
        return cleaningFrequency == CleaningFrequency.Weekly || cleaningFrequency == CleaningFrequency.BiWeekly || cleaningFrequency == CleaningFrequency.Monthly;
    }

    private boolean areServicesOverlapped(long days, String frequency){
        if(frequency.equals("Weekly"))
            return days % 7 == 0;
        if(frequency.equals("BiWeekly")){
            if(cleaningFrequency == CleaningFrequency.Weekly){
                return days % 7 == 0;
            }
            return days % 14 == 0;
        }
        if(frequency.equals("Monthly")){
            if(cleaningFrequency == CleaningFrequency.Weekly){
                return days % 7 == 0;
            }
            if(cleaningFrequency == CleaningFrequency.BiWeekly){
                return days % 14 == 0;
            }
            return days % 28 == 0;
        }
        return false;
    }

    private String getNextDateByFrequency(String date) {
        LocalDate currentDate = LocalDate.parse(date);
        switch(cleaningFrequency){
            case Weekly:
                return currentDate.plusDays(7L).toString();
            case BiWeekly:
                return currentDate.plusDays(14L).toString();
            case Monthly:
                return currentDate.plusDays(28L).toString();
            default:
                return null;
        }
    }
}