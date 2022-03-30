package com.cleaning.repository;

import com.cleaning.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface EmployeeRepository extends UserRepository<Employee> {
    @Query("Select cs From Employee e JOIN e.cleaningServices cs " +
            "on cs.employee.id = ?1 " +
            "where cs.cleaningDate.cleaningDate = ?2 " +
            "or (cs.cleaningFrequency = 'Weekly' and DATEDIFF(cs.cleaningDate.cleaningDate, ?2) < 0 and DATEDIFF(cs.cleaningDate.cleaningDate, ?2) % 7 = 0) " +
            "or (cs.cleaningFrequency = 'BiWeekly' and DATEDIFF(cs.cleaningDate.cleaningDate, ?2) < 0 and DATEDIFF(cs.cleaningDate.cleaningDate, ?2) % 14 = 0) " +
            "or (cs.cleaningFrequency = 'Monthly' and DATEDIFF(cs.cleaningDate.cleaningDate, ?2) < 0 and DATEDIFF(cs.cleaningDate.cleaningDate, ?2) % 28 = 0) ")
    List<CleaningService> getEmployeeCleaningServicesForDate(Long id, String date);
}
